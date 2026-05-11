package com.towerdefense.game.enemies;

import com.towerdefense.game.states.EnemyState;
import com.towerdefense.game.states.NormalState;

public abstract class Enemy {

    protected String name;
    protected int hp;
    protected float speed;
    protected int reward;
    protected int baseDamage;

    private float originalSpeed = -1; // Бастапқы жылдамдықты сақтаймыз
    private EnemyState state = new NormalState();

    public abstract void move();

    public void setState(EnemyState state) {
        this.state = state;
        this.state.handle(this); // Күй өзгергенде бірден іске қосылады
    }

    // Әр кадр сайын күйдің таймерін жүргізу
    public void update(float delta) {
        state.update(this, delta);
    }

    // Жылдамдықты басқару
    public void reduceSpeed(float factor) {
        if (originalSpeed == -1) originalSpeed = speed;
        speed = originalSpeed * factor;
    }

    public void restoreSpeed() {
        if (originalSpeed != -1) speed = originalSpeed;
    }

    public void takeDamage(int damage) { hp -= damage; }
    public boolean isDead() { return hp <= 0; }
    public String getCurrentState() { return state.getStateName(); }

    public String getInfo() {
        return name + " | HP:" + hp + " | SPD:" + speed
            + " | DMG:" + baseDamage + " | REWARD:" + reward + "g"
            + " | STATE:" + state.getStateName();
    }

    public String getName()    { return name; }
    public int getHp()         { return hp; }
    public float getSpeed()    { return speed; }
    public int getReward()     { return reward; }
    public int getBaseDamage() { return baseDamage; }
}
