package com.towerdefense.game.states;
import com.towerdefense.game.enemies.Enemy;

public class DeadState implements EnemyState {
    @Override
    public void handle(Enemy enemy) {}

    @Override
    public void update(Enemy enemy, float delta) {}

    @Override
    public String getStateName() { return "Dead"; }
}
