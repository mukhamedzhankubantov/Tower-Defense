package com.towerdefense.game.enemies;

public class Orc extends Enemy {

    public Orc() {
        this.name = "Orc";
        this.hp = 150;
        this.speed = 1.5f;
        this.reward = 20;
        this.baseDamage = 2;
    }

    @Override
    public void move() {
        System.out.println(name + " marches forward! SPD: " + speed);
    }
}
