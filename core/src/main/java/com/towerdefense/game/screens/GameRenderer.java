package com.towerdefense.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.towerdefense.game.core.GameEngine;

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
