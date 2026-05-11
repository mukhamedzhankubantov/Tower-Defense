package com.towerdefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.towerdefense.game.TowerDefenseGame;

public class MenuScreen implements Screen {

    private static final int V_WIDTH  = 1280;
    private static final int V_HEIGHT = 768;

    private final TowerDefenseGame game;
    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont font;
    private FitViewport viewport;
    private OrthographicCamera camera;
    private GlyphLayout layout;
    private Texture background;

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        camera.position.set(V_WIDTH / 2f, V_HEIGHT / 2f, 0);
        camera.update();
        batch = new SpriteBatch();
        titleFont = new BitmapFont();
        titleFont.getData().setScale(4f);
        titleFont.setColor(Color.YELLOW);
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.setColor(Color.WHITE);
        layout = new GlyphLayout();
        background = new Texture("images/menu_bg.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        viewport.apply();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
            || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
            || Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
            return;
        }

        batch.begin();

        batch.draw(background, 0, 0, V_WIDTH, V_HEIGHT);

        layout.setText(titleFont, "TOWER DEFENSE");
        titleFont.draw(batch, "TOWER DEFENSE",
            (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.7f);

        layout.setText(font, "Defend your base from waves of enemies!");
        font.draw(batch, "Defend your base from waves of enemies!",
            (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.45f);

        layout.setText(font, "Arrow(100g)  Cannon(150g)  Ice(125g)");
        font.draw(batch, "Arrow(100g)  Cannon(150g)  Ice(125g)",
            (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.35f);

        layout.setText(titleFont, "START");
        titleFont.draw(batch, "START",
            (V_WIDTH - layout.width) / 2f, V_HEIGHT * 0.22f);

        batch.end();
    }

    @Override public void resize(int width, int height) { viewport.update(width, height); }
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() { batch.dispose(); titleFont.dispose(); font.dispose(); background.dispose(); }
}
