package com.towerdefense.game.enemies;

public class BossFactory extends EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new Boss();
    }
}
