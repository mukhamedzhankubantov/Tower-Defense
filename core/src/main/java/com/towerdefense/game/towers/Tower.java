package com.towerdefense.game.towers;

import com.towerdefense.game.composite.TowerComponent;
import com.towerdefense.game.enemies.Enemy;
import com.towerdefense.game.strategies.AttackStrategy;
import java.util.List;

public abstract class Tower implements TowerComponent {

    protected String name;
    protected int damage;
    protected float range;
    protected int cost;
    protected float attackSpeed;

    private AttackStrategy attackStrategy;

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    public void performAttack(List<Enemy> enemiesInRange) {
        if (attackStrategy != null) {
            attackStrategy.execute(enemiesInRange);
        }
    }

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
