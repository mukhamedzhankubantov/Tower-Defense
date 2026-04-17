package com.towerdefense.game.towers;

public class TowerFactory {
    public Tower createTower(String type) {
        switch (type.toLowerCase()) {
            case "arrow":
                return new ArrowTower();
            case "cannon":
                return new CannonTower();
            case "ice":
                return new IceTower();
            default:
                throw new IllegalArgumentException("Unknown tower type: " + type);
        }
    }
}
