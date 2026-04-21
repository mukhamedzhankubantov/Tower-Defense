package com.towerdefense.game.decorators;

import com.towerdefense.game.towers.Tower;

public class DoubleDamageDecorator extends TowerDecorator {

    public DoubleDamageDecorator(Tower tower) {
        super(tower);
        this.damage = tower.getDamage() * 2;
        this.name = tower.getName() + " [x2 DMG]";
    }

    @Override
    public void attack() {
        System.out.println(name + " attacks with double damage: " + damage);
        wrappedTower.attack();
    }
}
