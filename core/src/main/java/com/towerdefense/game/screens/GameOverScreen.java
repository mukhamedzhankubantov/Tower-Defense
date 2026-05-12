package com.towerdefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.towerdefense.game.TowerDefenseGame;
import com.towerdefense.game.core.GameEngine;

public class GameOverScreen implements Screen {

    private static final int V_WIDTH  = 1280;
    private static final int V_HEIGHT = 768;

    private final TowerDefenseGame game;
    private final boolean playerWon;

    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont font;
    private FitViewport viewport;
    private OrthographicCamera camera;
    private GlyphLayout layout;

    public GameOverScreen(TowerDefenseGame game, boolean playerWon) {
        this.game = game;
        this.playerWon = playerWon;
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        camera.position.set(V_WIDTH / 2f, V_HEIGHT / 2f, 0);
        camera.update();
        batch = new SpriteBatch();
        titleFont = new BitmapFont();
        titleFont.getData().setScale(5f);
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);
        layout = new GlyphLayout();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f);
        viewport.apply();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()) {
            GameEngine.getInstance().reset();
            game.setScreen(new MenuScreen(game));
            dispose();
            return;
        }

        batch.begin();

        if (playerWon) {
            titleFont.setColor(Color.YELLOW);
            layout.setText(titleFont, "YOU WIN!");
        } else {
            titleFont.setColor(Color.RED);
            layout.setText(titleFont, "GAME OVER");
        }
        titleFont.draw(batch, layout, (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.65f);

        String sub = playerWon ? "10 rounds survived!" : "Your base was destroyed!";
        layout.setText(font, sub);
        font.draw(batch, sub, (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.42f);

        layout.setText(font, "START");
        font.draw(batch, "START", (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.25f);

        batch.end();
    }

    @Override public void resize(int width, int height) { viewport.update(width, height); }
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() { batch.dispose(); titleFont.dispose(); font.dispose(); }
}
