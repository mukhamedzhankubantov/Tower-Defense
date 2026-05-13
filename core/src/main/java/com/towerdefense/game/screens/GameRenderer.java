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
