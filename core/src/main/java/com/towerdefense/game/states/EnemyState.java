package com.towerdefense.game.states;

import com.towerdefense.game.enemies.Enemy;

public interface EnemyState {

    void handle(Enemy enemy);

    String getStateName();
}
