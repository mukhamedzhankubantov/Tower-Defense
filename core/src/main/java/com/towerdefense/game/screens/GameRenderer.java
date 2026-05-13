package com.towerdefense.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.towerdefense.game.core.GameEngine;
import com.towerdefense.game.map.GameMap;

public class GameRenderer {
    private final GameScreen screen;
    private final GameEngine engine = GameEngine.getInstance();

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private Texture grassTex, pathTex;
    private Texture arrowTex, cannonTex, iceTex;
    private Texture goblinTex, orcTex, bossTex;
    private Texture arrowProjTex, cannonProjTex, iceProjTex;

    public GameRenderer(GameScreen screen) {
        this.screen = screen;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        grassTex  = new Texture("images/grass.png");
        pathTex   = new Texture("images/path.png");
        arrowTex  = new Texture("images/arrow_tower.png");
        cannonTex = new Texture("images/cannon_tower.png");
        iceTex    = new Texture("images/ice_tower.png");
        goblinTex = new Texture("images/goblin.png");
        orcTex    = new Texture("images/orc.png");
        bossTex   = new Texture("images/boss.png");
        arrowProjTex = new Texture("images/arrow_projectile.png");
        cannonProjTex = new Texture("images/cannonball.png");
        iceProjTex = new Texture("images/ice_shard.png");

        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    public void render() {
        ScreenUtils.clear(0.15f, 0.2f, 0.15f, 1f);
        screen.getViewport().apply();
        screen.getCamera().update();
        batch.setProjectionMatrix(screen.getCamera().combined);
        shapeRenderer.setProjectionMatrix(screen.getCamera().combined);

        drawMap();
        drawShadows();
        drawTowers();
        drawShots();
        drawEnemies();
        drawHUD();

        if (screen.isPaused()) {
            drawPauseMenu();
        }
    }

    private void drawMap() {
        batch.begin();
        for (int col = 0; col < GameMap.COLS; col++) {
            for (int row = 0; row < GameMap.ROWS; row++) {
                Texture tex = screen.getMap().isTileOnPath(col, row) ? pathTex : grassTex;
                batch.draw(tex, col * GameMap.TILE_SIZE, row * GameMap.TILE_SIZE,
                    GameMap.TILE_SIZE, GameMap.TILE_SIZE);
            }
        }
        batch.end();
    }

    private void drawShadows() {
        com.badlogic.gdx.Gdx.gl.glEnable(com.badlogic.gdx.graphics.GL20.GL_BLEND);
        shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f, 0f, 0f, 0.4f);

        for (GameScreen.PlacedTower pt : screen.getTowers()) {
            shapeRenderer.ellipse(pt.x - 20, pt.y - 25, 40, 15);
        }

        for (GameScreen.ActiveEnemy ae : screen.getEnemies()) {
            shapeRenderer.ellipse(ae.x - 15, ae.y - 25, 30, 10);
        }

        shapeRenderer.end();
        com.badlogic.gdx.Gdx.gl.glDisable(com.badlogic.gdx.graphics.GL20.GL_BLEND);
    }

    private void drawTowers() {
        if (screen.getSelectedTower() != null) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1f, 1f, 0f, 1f);
            shapeRenderer.circle(screen.getSelectedTower().x, screen.getSelectedTower().y, screen.getSelectedTower().tower.getRange());
            shapeRenderer.end();
        }

        batch.begin();
        for (GameScreen.PlacedTower pt : screen.getTowers()) {
            String name = pt.tower.getName();
            Texture tex = name.contains("Arrow") ? arrowTex
                : name.contains("Cannon") ? cannonTex : iceTex;
            float size = GameMap.TILE_SIZE * 1.5f;
            batch.draw(tex, pt.x - size / 2f, pt.y - size / 2f, size, size);
        }
        batch.end();
    }

    private void drawShots() {
        if (screen.getShots().isEmpty()) return;

        batch.begin();
        for (GameScreen.Shot s : screen.getShots()) {
            float dx = s.x2 - s.x1;
            float dy = s.y2 - s.y1;
            float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
            float progress = 1f - (s.timer / 0.12f);
            float currentX = s.x1 + dx * progress;
            float currentY = s.y1 + dy * progress;

            Texture tex = arrowProjTex;
            if (s.type == 2) tex = cannonProjTex;
            else if (s.type == 3) tex = iceProjTex;

            batch.draw(tex, currentX - 16, currentY - 16, 16, 16, 32, 32, 1f, 1f, angle, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
        }
        batch.end();
    }

    private void drawEnemies() {
        batch.begin();
        for (GameScreen.ActiveEnemy ae : screen.getEnemies()) {
            String name = ae.enemy.getName();
            Texture tex = name.equals("Goblin") ? goblinTex
                : name.equals("Orc") ? orcTex : bossTex;
            batch.draw(tex, ae.x - 32, ae.y - 32, 64, 64);
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (GameScreen.ActiveEnemy ae : screen.getEnemies()) {
            float hpRatio = (float) ae.enemy.getHp() / getMaxHp(ae.enemy.getName());
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(ae.x - 24, ae.y + 28, 48, 6);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(ae.x - 24, ae.y + 28, 48 * hpRatio, 6);
        }
        shapeRenderer.end();
    }

    private int getMaxHp(String name) {
        if (name.equals("Goblin")) return 50;
        if (name.equals("Orc"))    return 150;
        return 500;
    }

    private void drawHUD() {
        int V_WIDTH = GameMap.COLS * GameMap.TILE_SIZE;
        int V_HEIGHT = GameMap.ROWS * GameMap.TILE_SIZE;

        batch.begin();

        float leftX = 15;
        float topY = V_HEIGHT - 15;

        font.setColor(Color.GOLD);
        font.draw(batch, "GOLD:  " + engine.getGold(), leftX, topY);
        font.setColor(Color.valueOf("FF6666"));
        font.draw(batch, "LIVES: " + engine.getLives(), leftX, topY - 25);
        font.setColor(Color.CYAN);
        font.draw(batch, "WAVE:  " + engine.getWave(), leftX, topY - 50);

        float shopY = topY - 90;
        font.setColor(Color.WHITE);
        font.draw(batch, "[1] Arrow  - 100g", leftX, shopY);
        font.draw(batch, "[2] Cannon - 150g", leftX, shopY - 25);
        font.draw(batch, "[3] Ice    - 125g", leftX, shopY - 50);

        font.setColor(Color.YELLOW);
        font.draw(batch, "[SPACE] Start Wave", leftX, shopY - 85);

        float infoY = shopY - 130;
        if (screen.getSelectedTower() != null) {
            font.setColor(Color.YELLOW);
            font.draw(batch, "- UPGRADE: " + screen.getSelectedTower().tower.getName() + " -", leftX, infoY);
            font.setColor(Color.WHITE);
            font.draw(batch, "[4] 2x Dmg (100g)", leftX, infoY - 25);
            font.draw(batch, "[5] Range (75g)", leftX, infoY - 50);
            font.draw(batch, "[6] Speed (100g)", leftX, infoY - 75);
        } else {
            int type = screen.getSelectedTowerType();
            String sel = type == 1 ? "Arrow" : type == 2 ? "Cannon" : "Ice Tower";
            font.setColor(Color.GREEN);
            font.draw(batch, "Building: " + sel, leftX, infoY);
            font.setColor(Color.LIGHT_GRAY);
            font.draw(batch, "Click tower to upgrade", leftX, infoY - 25);
        }

        batch.end();
    }

    private void drawPauseMenu() {
        int V_WIDTH = GameMap.COLS * GameMap.TILE_SIZE;
        int V_HEIGHT = GameMap.ROWS * GameMap.TILE_SIZE;

        com.badlogic.gdx.Gdx.gl.glEnable(com.badlogic.gdx.graphics.GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0f, 0f, 0f, 0.7f);
        shapeRenderer.rect(0, 0, V_WIDTH, V_HEIGHT);
        shapeRenderer.end();
        com.badlogic.gdx.Gdx.gl.glDisable(com.badlogic.gdx.graphics.GL20.GL_BLEND);

        batch.begin();
        font.getData().setScale(2.5f);
        font.setColor(Color.YELLOW);
        font.draw(batch, "PAUSED", V_WIDTH / 2f - 75, V_HEIGHT / 2f + 100);

        font.getData().setScale(1.5f);
        font.setColor(Color.WHITE);
        font.draw(batch, "Resume", V_WIDTH / 2f - 45, V_HEIGHT / 2f + 20);
        font.draw(batch, "Restart", V_WIDTH / 2f - 50, V_HEIGHT / 2f - 30);
        font.draw(batch, "Exit", V_WIDTH / 2f - 25, V_HEIGHT / 2f - 80);
        font.getData().setScale(1f);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        grassTex.dispose(); pathTex.dispose();
        arrowTex.dispose(); cannonTex.dispose(); iceTex.dispose();
        goblinTex.dispose(); orcTex.dispose(); bossTex.dispose();
        arrowProjTex.dispose(); cannonProjTex.dispose(); iceProjTex.dispose();
    }
}
