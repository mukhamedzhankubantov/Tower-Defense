package com.towerdefense.game.towers;

public class CannonTower extends Tower {

    public CannonTower() {
        this.name = "Cannon Tower";
        this.damage = 40;
        this.range = 100f;
        this.cost = 150;
        this.attackSpeed = 0.8f;
    }

    @Override
    public void attack() {
        System.out.println(name + " fires a cannonball! DMG: " + damage + " (splash)");
    }
}
