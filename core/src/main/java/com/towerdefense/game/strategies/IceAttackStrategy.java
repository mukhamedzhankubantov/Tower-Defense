package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import com.towerdefense.game.states.SlowedState;
import java.util.List;

public class IceAttackStrategy implements AttackStrategy {

    private final int damage = 5;

    @Override
    public void execute(List<Enemy> enemiesInRange) {
        for (Enemy enemy : enemiesInRange) {
            enemy.takeDamage(damage);
            enemy.setState(new SlowedState());
        }
    }
}
