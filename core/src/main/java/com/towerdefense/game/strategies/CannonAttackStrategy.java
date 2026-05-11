package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import com.towerdefense.game.towers.Tower;
import java.util.List;

public class CannonAttackStrategy implements AttackStrategy {
    @Override
    public void execute(Tower tower, List<Enemy> enemiesInRange) {
        for (Enemy enemy : enemiesInRange) {
            enemy.takeDamage(tower.getDamage());
        }
    }
}
