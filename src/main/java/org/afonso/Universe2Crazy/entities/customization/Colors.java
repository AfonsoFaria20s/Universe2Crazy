package org.afonso.Universe2Crazy.entities.customization;

import java.util.*;

public class Colors {
    public Map<String, Color> colors = new HashMap<>();

    public Colors() {
        addDefaultColors();
    }

    private void addDefaultColors() {
        addColor("universeBlue", "#3A8DFF");
        addColor("quantumPurple", "#A259FF");
        addColor("nebulaPink", "#FF5CA2");
        addColor("asteroidGrey", "#6E6E6E");
        addColor("darkVoid", "#1A1A1A");
    }

    public void addColor(String colorName, String colorCode) {
        Color newColor = new Color(colorName, colorCode);
        colors.put(colorName, newColor);
    }

    public void removeColor(String colorName) {
        colors.remove(colorName);
    }

    public boolean exists(String name) {
        return colors.containsKey(name);
    }

    public Color getColor(String colorName) {
        return colors.get(colorName);
    }

    public Map<String, Color> getAllColors() {
        return colors;
    }

    public String getListedColors() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Colors: \n");
        for (Map.Entry<String, Color> entry : colors.entrySet()) {
            sb
                    .append(entry.getValue().getName())
                    .append(": ")
                    .append(entry.getValue().getColorCode().getRed()+", ")
                    .append(entry.getValue().getColorCode().getGreen()+", ")
                    .append(entry.getValue().getColorCode().getBlue())
                    .append("\n");
        }

        return sb.toString();
    }

    public Color getRandomColorFromMap() {
        Random random = new Random();
        if (colors.isEmpty()) return null;

        List<Color> values = new ArrayList<>(colors.values());
        return values.get(random.nextInt(values.size()));
    }
}
