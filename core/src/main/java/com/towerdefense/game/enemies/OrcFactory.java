package com.towerdefense.game.enemies;

public class OrcFactory extends EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new Orc();
    }
}
