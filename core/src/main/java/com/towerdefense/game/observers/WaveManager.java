package com.towerdefense.game.observers;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {

    private final List<GameEventListener> listeners = new ArrayList<>();
    private int currentWave = 0;

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GameEventListener listener) {
        listeners.remove(listener);
    }

    public void startNextWave() {
        currentWave++;
        for (GameEventListener listener : listeners) {
            listener.onWaveStarted(currentWave);
        }
    }

    public void completeWave() {
        for (GameEventListener listener : listeners) {
            listener.onWaveCompleted(currentWave);
        }
    }

    public void notifyEnemyReachedEnd(int damage) {
        for (GameEventListener listener : listeners) {
            listener.onEnemyReachedEnd(damage);
        }
    }

    public void notifyEnemyKilled(int reward) {
        for (GameEventListener listener : listeners) {
            listener.onEnemyKilled(reward);
        }
    }

    public int getCurrentWave() { return currentWave; }
}
