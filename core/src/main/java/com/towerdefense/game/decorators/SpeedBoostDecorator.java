package com.towerdefense.game.decorators;

import com.towerdefense.game.towers.Tower;

public class SpeedBoostDecorator extends TowerDecorator {

    public SpeedBoostDecorator(Tower tower) {
        super(tower);
        this.attackSpeed = tower.getAttackSpeed() * 2.0f;
        this.name = tower.getName() + " [x2 SPD]";
    }

    @Override
    public void attack() {
        System.out.println(name + " attacks with boosted speed: " + attackSpeed);
        wrappedTower.attack();
    }
}
