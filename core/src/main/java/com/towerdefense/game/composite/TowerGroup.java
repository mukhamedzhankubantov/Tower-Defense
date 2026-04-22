package com.towerdefense.game.composite;

import java.util.ArrayList;
import java.util.List;

public class TowerGroup implements TowerComponent {

    private final String groupName;
    private final List<TowerComponent> towers = new ArrayList<>();

    public TowerGroup(String groupName) {
        this.groupName = groupName;
    }

    public void add(TowerComponent tower) {
        towers.add(tower);
    }

    public void remove(TowerComponent tower) {
        towers.remove(tower);
    }

    @Override
    public void attack() {
        for (TowerComponent tower : towers) {
            tower.attack();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder("Group [" + groupName + "]:\n");
        for (TowerComponent tower : towers) {
            sb.append("  ").append(tower.getInfo()).append("\n");
        }
        return sb.toString();
    }
}
