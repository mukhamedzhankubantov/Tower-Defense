package com.towerdefense.game.towers;

public class IceTower extends Tower {

    public IceTower() {
        this.name = "Ice Tower";
        this.damage = 5;
        this.range = 130f;
        this.cost = 125;
        this.attackSpeed = 1.5f;
    }

    @Override
    public void attack() {
        System.out.println(name + " shoots ice! DMG: " + damage + " (slows enemies)");
    }
}
