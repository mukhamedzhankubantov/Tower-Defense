package com.towerdefense.game.states;
import com.towerdefense.game.enemies.Enemy;

public class NormalState implements EnemyState {
    @Override
    public void handle(Enemy enemy) {}

    @Override
    public void update(Enemy enemy, float delta) {} // Қалыпты күйде ештеңе өзгермейді

    @Override
    public String getStateName() { return "Normal"; }
}
