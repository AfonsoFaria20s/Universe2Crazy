package org.afonso.Universe2Crazy.entities.customization;

public class Color {
    private String name;
    private java.awt.Color colorCode;

    public Color(String name, String colorCode) {
        this.name = name;
        this.colorCode = parseHexColor(colorCode);
    }

    private java.awt.Color parseHexColor(String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        return new java.awt.Color(r, g, b);
    }

    public String getName() {
        return name;
    }

    public java.awt.Color getColorCode() {
        return colorCode;
    }
}
