package com.towerdefense.game.towers;

public class IceTowerFactory extends TowerFactory {

    @Override
    public Tower createTower() {
        return new IceTower();
    }
}
