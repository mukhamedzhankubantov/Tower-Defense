package com.towerdefense.game.states;

import com.towerdefense.game.enemies.Enemy;

public class NormalState implements EnemyState {

    @Override
    public void handle(Enemy enemy) {
        System.out.println(enemy.getName() + " is moving normally at speed: " + enemy.getSpeed());
    }

    @Override
    public String getStateName() {
        return "Normal";
    }
}
