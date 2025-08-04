package org.afonso.Universe2Crazy.entities;

import org.afonso.Universe2Crazy.entities.customization.Color;
import org.afonso.Universe2Crazy.entities.customization.Colors;

import javax.swing.*;

public class UniversePanel extends JPanel {

    private Color backgroundColor;
    private Colors colors;

    public UniversePanel(Colors colors, Color color) {
        this.colors = colors;
        this.backgroundColor = color; // Default color

        this.setBackground(color.getColorCode());
        this.setVisible(true);
    }

    public void changeBackgroundColor(String newColor) {
        this.backgroundColor = colors.getColor(newColor);
        this.setBackground(colors.getColor(newColor).getColorCode());
        this.revalidate();
    }
}
