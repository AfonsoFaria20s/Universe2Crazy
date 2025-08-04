package org.afonso.Universe2Crazy.entities;

import org.afonso.Universe2Crazy.console.HistoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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

    public void initiateBigBang() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        Map<UniverseFrame, Point> frames = new HashMap<>();
        for (Universe u : universes.values()) {
            UniverseFrame f = u.getUniverseFrame();
            frames.put(f, f.getLocation());
        }

        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            int ticks = 0;
            boolean allCentered = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                ticks++;

                for (Map.Entry<UniverseFrame, Point> entry : frames.entrySet()) {
                    UniverseFrame frame = entry.getKey();
                    Point loc = frame.getLocation();

                    // Calculate direction
                    int dx = centerX - (loc.x + frame.getWidth() / 2);
                    int dy = centerY - (loc.y + frame.getHeight() / 2);

                    // Apply movement
                    if (Math.abs(dx) > 5 || Math.abs(dy) > 5) {
                        loc.translate(dx / 10, dy / 10);
                    } else {
                        // Start shaking
                        int shakeX = (int)(Math.random() * 10 - 5);
                        int shakeY = (int)(Math.random() * 10 - 5);
                        loc.translate(shakeX, shakeY);
                    }

                    frame.setLocation(loc);
                }

                // Verify if frames are centered
                allCentered = frames.keySet().stream().allMatch(f ->
                        Math.abs(centerX - (f.getX() + f.getWidth()/2)) < 10 &&
                                Math.abs(centerY - (f.getY() + f.getHeight()/2)) < 10
                );

                if (allCentered && ticks > 60) { // After 60 ticks (~1s)
                    timer.stop();
                    System.exit(0);
                }
            }
        });

        timer.start();
    }

}
