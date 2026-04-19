package com.towerdefense.game.observers;

public interface GameEventListener {

    void onWaveStarted(int waveNumber);

    void onWaveCompleted(int waveNumber);

    void onEnemyReachedEnd(int damage);

    void onEnemyKilled(int reward);
}
