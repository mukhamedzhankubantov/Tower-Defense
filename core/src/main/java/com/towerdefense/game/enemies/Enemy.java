package com.towerdefense.game.enemies;

public abstract class Enemy {

    protected String name;
    protected int hp;
    protected float speed;
    protected int reward;
    protected int baseDamage;

    public abstract void move();

    public String getInfo() {
        return name + " | HP:" + hp + " | SPD:" + speed
                + " | DMG:" + baseDamage + " | REWARD:" + reward + "g";
    }

    public String getName()    { return name; }
    public int getHp()         { return hp; }
    public float getSpeed()    { return speed; }
    public int getReward()     { return reward; }
    public int getBaseDamage() { return baseDamage; }
}
