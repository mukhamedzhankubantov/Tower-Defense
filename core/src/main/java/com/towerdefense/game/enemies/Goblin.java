package com.towerdefense.game.enemies;

public class Goblin extends Enemy {

    public Goblin() {
        this.name = "Goblin";
        this.hp = 50;
        this.speed = 3.0f;
        this.reward = 10;
        this.baseDamage = 1;
    }

    @Override
    public void move() {
        System.out.println(name + " runs fast! SPD: " + speed);
    }
}
