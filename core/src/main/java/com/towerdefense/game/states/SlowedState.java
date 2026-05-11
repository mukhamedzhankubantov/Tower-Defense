package com.towerdefense.game.states;
import com.towerdefense.game.enemies.Enemy;

public class SlowedState implements EnemyState {
    private float timer = 0f;
    private final float slowDuration = 2.5f; // 2.5 секундқа баяулатады
    private boolean applied = false;

    @Override
    public void handle(Enemy enemy) {
        if (!applied) {
            enemy.reduceSpeed(0.5f); // Жылдамдығын 2 есе азайту
            applied = true;
        }
    }

    @Override
    public void update(Enemy enemy, float delta) {
        timer += delta;
        if (timer >= slowDuration) {
            enemy.restoreSpeed(); // Уақыт біткенде жылдамдығын қайтару
            enemy.setState(new NormalState()); // Қалыпты күйге қайту
        }
    }

    @Override
    public String getStateName() { return "Slowed"; }
}
