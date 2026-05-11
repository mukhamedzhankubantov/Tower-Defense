package com.towerdefense.game;

import com.badlogic.gdx.Game;
import com.towerdefense.game.screens.MenuScreen;

public class TowerDefenseGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
