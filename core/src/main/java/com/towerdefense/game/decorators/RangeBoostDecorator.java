package com.towerdefense.game.decorators;

import com.towerdefense.game.towers.Tower;

public class RangeBoostDecorator extends TowerDecorator {

    public RangeBoostDecorator(Tower tower) {
        super(tower);
        this.range = tower.getRange() * 1.5f;
        this.name = tower.getName() + " [+50% RNG]";
    }

    @Override
    public void attack() {
        System.out.println(name + " attacks with boosted range: " + range);
        wrappedTower.attack();
    }
}
