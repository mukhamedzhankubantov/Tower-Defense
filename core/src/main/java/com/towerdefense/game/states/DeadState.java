package com.towerdefense.game.states;

import com.towerdefense.game.enemies.Enemy;

public class DeadState implements EnemyState {

    @Override
    public void handle(Enemy enemy) {
        System.out.println(enemy.getName() + " is dead. Reward: " + enemy.getReward() + "g");
    }

    @Override
    public String getStateName() {
        return "Dead";
    }
}
