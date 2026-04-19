package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import java.util.List;

public class IceAttackStrategy implements AttackStrategy {

    private final int damage = 5;
    private final float slowFactor = 0.5f;

    @Override
    public void execute(List<Enemy> enemiesInRange) {
        for (Enemy enemy : enemiesInRange) {
            System.out.println("Ice hits " + enemy.getName() + " for " + damage
                    + " DMG, slowed to " + (enemy.getSpeed() * slowFactor));
        }
    }
}
