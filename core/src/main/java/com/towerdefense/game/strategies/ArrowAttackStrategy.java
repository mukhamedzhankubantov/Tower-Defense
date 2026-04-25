package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import java.util.List;

public class ArrowAttackStrategy implements AttackStrategy {

    private final int damage = 10;

    @Override
    public void execute(List<Enemy> enemiesInRange) {
        if (enemiesInRange.isEmpty()) return;
        Enemy target = enemiesInRange.get(0);
        target.takeDamage(damage);
    }
}
