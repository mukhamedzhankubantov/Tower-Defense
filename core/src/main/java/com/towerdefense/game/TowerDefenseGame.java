package com.towerdefense.game;

import com.badlogic.gdx.Game;
import com.towerdefense.game.screens.GameScreen;

public class TowerDefenseGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
