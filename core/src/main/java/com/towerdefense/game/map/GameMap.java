package com.towerdefense.game.map;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class GameMap {

    public static final int TILE_SIZE = 64;
    public static final int COLS = 20;
    public static final int ROWS = 12;

    private final List<Vector2> path = new ArrayList<>();

    public GameMap() {
        buildPath();
    }

    private void buildPath() {
        path.add(new Vector2(0, 5));
        path.add(new Vector2(4, 5));
        path.add(new Vector2(4, 2));
        path.add(new Vector2(10, 2));
        path.add(new Vector2(10, 9));
        path.add(new Vector2(16, 9));
        path.add(new Vector2(16, 5));
        path.add(new Vector2(20, 5));
    }

    public List<Vector2> getPath() {
        return path;
    }

    public boolean isTileOnPath(int col, int row) {
        for (int i = 0; i < path.size() - 1; i++) {
            Vector2 from = path.get(i);
            Vector2 to = path.get(i + 1);
            if (isBetween(col, row, from, to)) return true;
        }
        return false;
    }

    private boolean isBetween(int col, int row, Vector2 from, Vector2 to) {
        if (from.x == to.x) {
            int minY = (int) Math.min(from.y, to.y);
            int maxY = (int) Math.max(from.y, to.y);
            return col == (int) from.x && row >= minY && row <= maxY;
        } else {
            int minX = (int) Math.min(from.x, to.x);
            int maxX = (int) Math.max(from.x, to.x);
            return row == (int) from.y && col >= minX && col <= maxX;
        }
    }
}
