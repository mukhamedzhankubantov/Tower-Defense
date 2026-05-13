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

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        camera.position.set(V_WIDTH / 2f, V_HEIGHT / 2f, 0);
        camera.update();
        waveManager.addListener(this);
        path = map.getPath();
    }

    public OrthographicCamera getCamera() { return camera; }
    public FitViewport getViewport() { return viewport; }
    public GameMap getMap() { return map; }
    public List<PlacedTower> getTowers() { return towers; }
    public List<ActiveEnemy> getEnemies() { return enemies; }
    public List<Shot> getShots() { return shots; }
}
