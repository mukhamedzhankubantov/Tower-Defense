package com.towerdefense.game.enemies;

public class Boss extends Enemy {

    public Boss() {
        this.name = "Boss";
        this.hp = 500;
        this.speed = 0.8f;
        this.reward = 100;
        this.baseDamage = 5;
    }

    @Override
    public void move() {
        System.out.println(name + " stomps slowly! SPD: " + speed);
    }
}
