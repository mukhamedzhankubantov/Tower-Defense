package com.towerdefense.game.states;

import com.towerdefense.game.enemies.Enemy;

public interface EnemyState {
    void handle(Enemy enemy);
    void update(Enemy enemy, float delta); // Таймер үшін
    String getStateName();
}
