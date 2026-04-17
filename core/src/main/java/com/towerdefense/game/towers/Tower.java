package com.towerdefense.game.towers;

public abstract class Tower {

    protected String name;
    protected int damage;
    protected float range;
    protected int cost;
    protected float attackSpeed;

    public abstract void attack();

    public String getInfo() {
        return name + " | DMG:" + damage + " | RNG:" + range
                + " | SPD:" + attackSpeed + " | COST:" + cost + "g";
    }

    public String getName()       { return name; }
    public int getDamage()        { return damage; }
    public float getRange()       { return range; }
    public int getCost()          { return cost; }
    public float getAttackSpeed() { return attackSpeed; }
}
