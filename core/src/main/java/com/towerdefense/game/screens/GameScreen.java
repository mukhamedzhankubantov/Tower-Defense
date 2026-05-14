package com.towerdefense.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.towerdefense.game.TowerDefenseGame;
import com.towerdefense.game.core.GameEngine;
import com.towerdefense.game.enemies.Enemy;
import com.towerdefense.game.map.GameMap;
import com.towerdefense.game.observers.WaveManager;
import com.towerdefense.game.observers.GameEventListener;
import com.towerdefense.game.towers.Tower;
import com.badlogic.gdx.utils.Pool;
import com.towerdefense.game.decorators.DoubleDamageDecorator;
import com.towerdefense.game.decorators.RangeBoostDecorator;
import com.towerdefense.game.decorators.SpeedBoostDecorator;
import com.towerdefense.game.strategies.ArrowAttackStrategy;
import com.towerdefense.game.strategies.CannonAttackStrategy;
import com.towerdefense.game.strategies.IceAttackStrategy;
import com.towerdefense.game.towers.ArrowTowerFactory;
import com.towerdefense.game.towers.CannonTowerFactory;
import com.towerdefense.game.towers.IceTowerFactory;
import com.towerdefense.game.enemies.GoblinFactory;
import com.towerdefense.game.enemies.OrcFactory;
import com.towerdefense.game.enemies.BossFactory;
import com.towerdefense.game.states.DeadState;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen, GameEventListener {

    public static class PlacedTower {
        public Tower tower;
        public float x, y;
        public float attackTimer = 0f;
        public PlacedTower(Tower t, float x, float y) { tower = t; this.x = x; this.y = y; }
    }

    public static class Shot implements Pool.Poolable {
        public int type;
        public float x1, y1, x2, y2;
        public float timer;
        public Shot() {}
        public void init(int type, float x1, float y1, float x2, float y2) {
            this.type = type;
            this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
            this.timer = 0.12f;
        }
        @Override
        public void reset() { this.timer = 0f; this.type = 0; }
    }

    public static class ActiveEnemy {
        public Enemy enemy;
        public float x, y;
        public int waypointIndex = 1;
        public ActiveEnemy(Enemy e, float x, float y) { enemy = e; this.x = x; this.y = y; }
    }

    private final TowerDefenseGame game;
    private final GameEngine engine = GameEngine.getInstance();

    private static final int V_WIDTH  = GameMap.COLS * GameMap.TILE_SIZE;
    private static final int V_HEIGHT = GameMap.ROWS * GameMap.TILE_SIZE;

    private OrthographicCamera camera;
    private FitViewport viewport;

    private final GameMap map = new GameMap();
    private final WaveManager waveManager = new WaveManager();
    private final List<Vector2> path;

    private final List<PlacedTower> towers = new ArrayList<>();
    private final List<ActiveEnemy> enemies = new ArrayList<>();
    private final List<Shot> shots = new ArrayList<>();

    private final Pool<Shot> shotPool = new Pool<Shot>() {
        @Override
        protected Shot newObject() { return new Shot(); }
    };

    private int selectedTowerType = 1;
    private PlacedTower selectedTower = null;
    private boolean isPaused = false;

    private float spawnTimer = 0f;
    private final float spawnInterval = 2f;
    private int enemiesToSpawn = 0;
    private boolean waveActive = false;

    private final PlayerInputHandler inputHandler;
    private final GameRenderer renderer;

    public boolean isPaused() { return isPaused; }
    public void setPaused(boolean p) { isPaused = p; }

    public void restartGame() {
        engine.reset();
        game.setScreen(new GameScreen(game));
        this.dispose();
    }

    public void exitToMenu() {
        game.setScreen(new MenuScreen(game));
        this.dispose();
    }

    public void startWave() {
        if (!waveActive) {
            engine.nextWave();
            enemiesToSpawn = 5 + engine.getWave() * 2;
            waveActive = true;
            waveManager.startNextWave();
        }
    }

    public void upgradeSelectedTower(int upgradeType) {
        if (selectedTower == null) return;
        if (upgradeType == 4 && engine.getGold() >= 100) {
            engine.spendGold(100);
            selectedTower.tower = new DoubleDamageDecorator(selectedTower.tower);
        } else if (upgradeType == 5 && engine.getGold() >= 75) {
            engine.spendGold(75);
            selectedTower.tower = new RangeBoostDecorator(selectedTower.tower);
        } else if (upgradeType == 6 && engine.getGold() >= 100) {
            engine.spendGold(100);
            selectedTower.tower = new SpeedBoostDecorator(selectedTower.tower);
        }
    }

    private void placeTower(int col, int row) {
        if (col < 0 || col >= GameMap.COLS || row < 0 || row >= GameMap.ROWS) return;
        if (map.isTileOnPath(col, row)) return;

        Tower tower;
        int cost;
        if (selectedTowerType == 1) {
            tower = new ArrowTowerFactory().createTower();
            tower.setAttackStrategy(new ArrowAttackStrategy());
            cost = 100;
        } else if (selectedTowerType == 2) {
            tower = new CannonTowerFactory().createTower();
            tower.setAttackStrategy(new CannonAttackStrategy());
            cost = 150;
        } else {
            tower = new IceTowerFactory().createTower();
            tower.setAttackStrategy(new IceAttackStrategy());
            cost = 125;
        }

        if (engine.getGold() >= cost) {
            engine.spendGold(cost);
            float wx = col * GameMap.TILE_SIZE + GameMap.TILE_SIZE / 2f;
            float wy = row * GameMap.TILE_SIZE + GameMap.TILE_SIZE / 2f;
            towers.add(new PlacedTower(tower, wx, wy));
        }
    }

    public void processTouch(float wx, float wy) {
        PlacedTower clicked = null;
        for (PlacedTower pt : towers) {
            if (Math.abs(pt.x - wx) <= 32 && Math.abs(pt.y - wy) <= 32) {
                clicked = pt;
                break;
            }
        }

        if (clicked != null) {
            selectedTower = (selectedTower == clicked) ? null : clicked;
        } else {
            selectedTower = null;
            int col = (int) wx / GameMap.TILE_SIZE;
            int row = (int) wy / GameMap.TILE_SIZE;
            placeTower(col, row);
        }
    }

    @Override
    public void render(float delta) {
        inputHandler.handleInput();
        update(delta);
        renderer.render();
    }

    private void update(float delta) {
        if (isPaused) return;
        if (engine.isGameOver()) return;

        if (waveActive) {
            spawnTimer += delta;
            if (spawnTimer >= spawnInterval && enemiesToSpawn > 0) {
                spawnTimer = 0;
                spawnEnemy();
                enemiesToSpawn--;
            }
        }

        Iterator<ActiveEnemy> it = enemies.iterator();
        while (it.hasNext()) {
            ActiveEnemy ae = it.next();
            ae.enemy.update(delta);
            boolean reached = moveEnemy(ae, delta);

            if (reached) {
                waveManager.notifyEnemyReachedEnd(ae.enemy.getBaseDamage());
                it.remove();
                continue;
            }

            for (PlacedTower pt : towers) {
                pt.attackTimer += delta;
                float cooldown = 1f / pt.tower.getAttackSpeed();
                float dist = Vector2.dst(pt.x, pt.y, ae.x, ae.y);
                if (dist <= pt.tower.getRange() && pt.attackTimer >= cooldown) {
                    pt.attackTimer = 0f;
                    List<Enemy> nearby = new ArrayList<>();
                    nearby.add(ae.enemy);
                    pt.tower.performAttack(nearby);

                    Shot s = shotPool.obtain();
                    int tType = pt.tower.getName().contains("Arrow") ? 1
                        : pt.tower.getName().contains("Cannon") ? 2 : 3;
                    s.init(tType, pt.x, pt.y, ae.x, ae.y);
                    shots.add(s);
                }
            }

            if (ae.enemy.isDead()) {
                ae.enemy.setState(new DeadState());
                engine.addGold(ae.enemy.getReward());
                waveManager.notifyEnemyKilled(ae.enemy.getReward());
                it.remove();
            }
        }

        if (waveActive && enemiesToSpawn == 0 && enemies.isEmpty()) {
            waveActive = false;
            waveManager.completeWave();
            if (engine.getWave() >= 10) {
                game.setScreen(new GameOverScreen(game, true));
                return;
            }
        }

        if (engine.isGameOver()) {
            game.setScreen(new GameOverScreen(game, false));
        }

        Iterator<Shot> shotIt = shots.iterator();
        while (shotIt.hasNext()) {
            Shot s = shotIt.next();
            s.timer -= delta;
            if (s.timer <= 0) {
                shotIt.remove();
                shotPool.free(s);
            }
        }
    }

    private boolean moveEnemy(ActiveEnemy ae, float delta) {
        if (ae.waypointIndex >= path.size()) return true;

        Vector2 target = path.get(ae.waypointIndex);
        float tx = target.x * GameMap.TILE_SIZE + GameMap.TILE_SIZE / 2f;
        float ty = target.y * GameMap.TILE_SIZE + GameMap.TILE_SIZE / 2f;

        float dx = tx - ae.x;
        float dy = ty - ae.y;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        float step = ae.enemy.getSpeed() * GameMap.TILE_SIZE * delta;

        if (dist <= step) {
            ae.x = tx; ae.y = ty;
            ae.waypointIndex++;
        } else {
            ae.x += (dx / dist) * step;
            ae.y += (dy / dist) * step;
        }

        return ae.waypointIndex >= path.size();
    }

    private void spawnEnemy() {
        int wave = engine.getWave();
        double r = Math.random();
        Enemy enemy;
        if (wave == 1) {
            enemy = new GoblinFactory().createEnemy();
        } else if (wave == 2) {
            enemy = r < 0.6 ? new GoblinFactory().createEnemy() : new OrcFactory().createEnemy();
        } else {
            if (r < 0.3)      enemy = new GoblinFactory().createEnemy();
            else if (r < 0.7) enemy = new OrcFactory().createEnemy();
            else               enemy = new BossFactory().createEnemy();
        }
        Vector2 start = path.get(0);
        float sx = start.x * GameMap.TILE_SIZE + GameMap.TILE_SIZE / 2f;
        float sy = start.y * GameMap.TILE_SIZE + GameMap.TILE_SIZE / 2f;
        enemies.add(new ActiveEnemy(enemy, sx, sy));
    }

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        camera.position.set(V_WIDTH / 2f, V_HEIGHT / 2f, 0);
        camera.update();
        waveManager.addListener(this);
        path = map.getPath();

        this.inputHandler = new PlayerInputHandler(this);
        this.renderer = new GameRenderer(this);
    }

    public int getSelectedTowerType() { return selectedTowerType; }
    public void setSelectedTowerType(int type) { this.selectedTowerType = type; }
    public OrthographicCamera getCamera() { return camera; }
    public FitViewport getViewport() { return viewport; }
    public GameMap getMap() { return map; }
    public List<PlacedTower> getTowers() { return towers; }
    public List<ActiveEnemy> getEnemies() { return enemies; }
    public List<Shot> getShots() { return shots; }
    public PlacedTower getSelectedTower() { return selectedTower; }
}
