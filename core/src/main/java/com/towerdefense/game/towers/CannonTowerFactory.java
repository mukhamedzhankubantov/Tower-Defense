package com.towerdefense.game.towers;

public class CannonTowerFactory extends TowerFactory {

    @Override
    public Tower createTower() {
        return new CannonTower();
    }
}
