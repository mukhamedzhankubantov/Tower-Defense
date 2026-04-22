package com.towerdefense.game.states;

import com.towerdefense.game.enemies.Enemy;

public class SlowedState implements EnemyState {

    private final float slowFactor = 0.5f;

    @Override
    public void handle(Enemy enemy) {
        System.out.println(enemy.getName() + " is slowed! Speed: " + (enemy.getSpeed() * slowFactor));
    }

    @Override
    public String getStateName() {
        return "Slowed";
    }
}
