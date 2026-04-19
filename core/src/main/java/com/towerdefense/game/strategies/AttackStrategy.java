package com.towerdefense.game.strategies;

import com.towerdefense.game.enemies.Enemy;
import java.util.List;

public interface AttackStrategy {

    void execute(List<Enemy> enemiesInRange);
}
