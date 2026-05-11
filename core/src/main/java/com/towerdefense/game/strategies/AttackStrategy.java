package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import com.towerdefense.game.towers.Tower;
import java.util.List;

public interface AttackStrategy {
    void execute(Tower tower, List<Enemy> enemiesInRange);
}
