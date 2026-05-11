package com.towerdefense.game.core;

public class GameEngine {

    private static GameEngine instance;

    private int lives = 20;
    private int gold  = 150;
    private int wave  = 0;

    private GameEngine() { }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void spendGold(int amount) { gold -= amount; }
    public void addGold(int amount) { gold += amount; }
    public void loseLife() { lives--; }
    public boolean isGameOver() { return lives <= 0; }
    public void nextWave() { wave++; }

    public int getLives() { return lives; }
    public int getGold()  { return gold; }
    public int getWave()  { return wave; }

    // Ойынды қайта бастағанда мәліметтерді қалпына келтіру үшін
    public void reset() {
        this.gold = 150;
        this.wave = 0;
        this.lives = 20;
    }
}
