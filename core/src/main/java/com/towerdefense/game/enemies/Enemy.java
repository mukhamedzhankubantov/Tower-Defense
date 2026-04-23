package com.towerdefense.game.enemies;

import com.towerdefense.game.states.EnemyState;
import com.towerdefense.game.states.NormalState;

public abstract class Enemy {

    protected String name;
    protected int hp;
    protected float speed;
    protected int reward;
    protected int baseDamage;

    private EnemyState state = new NormalState();

    public abstract void move();

    public void setState(EnemyState state) {
        this.state = state;
    }

    public void updateState() {
        state.handle(this);
    }

    public String getCurrentState() {
        return state.getStateName();
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public boolean isDead() {
        return hp <= 0;
    }

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
