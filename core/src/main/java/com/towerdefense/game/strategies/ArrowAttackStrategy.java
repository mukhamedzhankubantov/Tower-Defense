package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import com.towerdefense.game.towers.Tower;
import java.util.List;

public class ArrowAttackStrategy implements AttackStrategy {
    @Override
    public void execute(Tower tower, List<Enemy> enemiesInRange) {
        if (enemiesInRange.isEmpty()) return;
        Enemy target = enemiesInRange.get(0);
        target.takeDamage(tower.getDamage());
    }
}
