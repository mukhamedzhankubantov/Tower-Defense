package com.towerdefense.game.towers;

public class ArrowTower extends Tower {

    public ArrowTower() {
        this.name = "Arrow Tower";
        this.damage = 10;
        this.range = 150f;
        this.cost = 100;
        this.attackSpeed = 2.0f;
    }

    @Override
    public void attack() {
        System.out.println(name + " shoots an arrow! DMG: " + damage);
    }
}
