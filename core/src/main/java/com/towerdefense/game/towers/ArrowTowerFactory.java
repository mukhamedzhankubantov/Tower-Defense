package com.towerdefense.game.towers;

public class ArrowTowerFactory extends TowerFactory {

    @Override
    public Tower createTower() {
        return new ArrowTower();
    }
}
