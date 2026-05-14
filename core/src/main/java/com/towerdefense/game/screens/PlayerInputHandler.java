package com.towerdefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.towerdefense.game.map.GameMap;

public class PlayerInputHandler {

    private final GameScreen screen;

    public PlayerInputHandler(GameScreen screen) {
        this.screen = screen;
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            screen.setPaused(!screen.isPaused());
        }

        if (screen.isPaused()) {
            if (Gdx.input.justTouched()) {
                Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                screen.getCamera().unproject(touch, screen.getViewport().getScreenX(), screen.getViewport().getScreenY(),
                    screen.getViewport().getScreenWidth(), screen.getViewport().getScreenHeight());

                float wy = touch.y;
                int cy = (GameMap.ROWS * GameMap.TILE_SIZE) / 2;

                if (wy > cy && wy < cy + 40) {
                    screen.setPaused(false);
                } else if (wy > cy - 50 && wy < cy - 10) {
                    screen.restartGame();
                } else if (wy > cy - 100 && wy < cy - 60) {
                    screen.exitToMenu();
                }
            }
            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) screen.setSelectedTowerType(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) screen.setSelectedTowerType(2);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) screen.setSelectedTowerType(3);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) screen.startWave();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) screen.upgradeSelectedTower(4);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) screen.upgradeSelectedTower(5);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) screen.upgradeSelectedTower(6);

        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            screen.getCamera().unproject(touch, screen.getViewport().getScreenX(), screen.getViewport().getScreenY(),
                screen.getViewport().getScreenWidth(), screen.getViewport().getScreenHeight());
            screen.processTouch(touch.x, touch.y);
        }
    }
}
