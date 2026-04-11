package com.towerdefense.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.towerdefense.game.TowerDefenseGame;
import com.towerdefense.game.core.GameEngine;

public class GameScreen implements Screen {

    private final TowerDefenseGame game;
    private SpriteBatch batch;

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f);

        // Singleton арқылы GameEngine-ге қол жеткіземіз
        GameEngine engine = GameEngine.getInstance();

        if (engine.isGameOver()) {
            // кейін GameOverScreen қосамыз
            System.out.println("Game Over!");
        }

        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}
