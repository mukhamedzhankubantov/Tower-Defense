package com.towerdefense.game.decorators;

import com.towerdefense.game.towers.Tower;

public abstract class TowerDecorator extends Tower {

    protected Tower wrappedTower;

    public TowerDecorator(Tower tower) {
        this.wrappedTower = tower;
        this.name = tower.getName();
        this.damage = tower.getDamage();
        this.range = tower.getRange();
        this.cost = tower.getCost();
        this.attackSpeed = tower.getAttackSpeed();

        // Ату логикасын да көшіріп аламыз!
        this.setAttackStrategy(tower.getAttackStrategy());
    }

    @Override
    public void attack() {
        wrappedTower.attack();
    }
}
