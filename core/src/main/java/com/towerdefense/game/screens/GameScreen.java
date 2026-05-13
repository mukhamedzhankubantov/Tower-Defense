package com.towerdefense.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.towerdefense.game.TowerDefenseGame;
import com.towerdefense.game.core.GameEngine;
import com.towerdefense.game.map.GameMap;
import com.towerdefense.game.observers.WaveManager;
import com.towerdefense.game.observers.GameEventListener;

import java.util.List;

public class GameScreen implements Screen, GameEventListener {

    private final TowerDefenseGame game;
    private final GameEngine engine = GameEngine.getInstance();

    private static final int V_WIDTH  = GameMap.COLS * GameMap.TILE_SIZE;
    private static final int V_HEIGHT = GameMap.ROWS * GameMap.TILE_SIZE;

    private OrthographicCamera camera;
    private FitViewport viewport;

    private final GameMap map = new GameMap();
    private final WaveManager waveManager = new WaveManager();
    private final List<Vector2> path;

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        camera.position.set(V_WIDTH / 2f, V_HEIGHT / 2f, 0);
        camera.update();
        waveManager.addListener(this);
        path = map.getPath();
    }

    public OrthographicCamera getCamera() { return camera; }
    public FitViewport getViewport() { return viewport; }
    public GameMap getMap() { return map; }
}
