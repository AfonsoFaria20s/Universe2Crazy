package org.afonso.Universe2Crazy.entities;

import org.afonso.Universe2Crazy.console.HistoryPanel;

import java.util.LinkedHashMap;
import java.util.Map;

public class UniverseManager {
    private HistoryPanel historyPanel;

    Map<String, Universe> universes = new LinkedHashMap<>();

    public UniverseManager(HistoryPanel historyPanel) {
        this.historyPanel = historyPanel;
    }

    public void addUniverse(Universe universe, String id) {
        universes.put(id, universe);
    }

    public int removeUniverse(String id) {
        if(!universes.containsKey(id)) return -1;

        universes.get(id).getUniverseFrame().dispose();
        universes.remove(id);
        return 0;
    }

    public int explodeUniverse(String id) {
        if(!universes.containsKey(id)) return -1;

        universes.get(id).getUniverseFrame().explode();
        return 0;
    }

    public String getListedUniverses() {
        StringBuilder stringBuilder = new StringBuilder("Available Universes: \n");
        for (Map.Entry<String, Universe> universeEntry : universes.entrySet()) {
            stringBuilder
                    .append(universeEntry.getKey())
                    .append(": ").append(universeEntry.getValue().getName())
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    public Map<String, Universe> getUniverses() {
        return universes;
    }
}
