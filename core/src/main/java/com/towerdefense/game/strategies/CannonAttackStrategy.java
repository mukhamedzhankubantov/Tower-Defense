package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import java.util.List;

public class CannonAttackStrategy implements AttackStrategy {

    private final int damage = 40;

    @Override
    public void execute(List<Enemy> enemiesInRange) {
        for (Enemy enemy : enemiesInRange) {
            System.out.println("Cannon hits " + enemy.getName() + " for " + damage + " DMG (splash)");
        }
    }
}
