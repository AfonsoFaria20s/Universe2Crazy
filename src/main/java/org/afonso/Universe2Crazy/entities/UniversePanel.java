package org.afonso.Universe2Crazy.entities;

import org.afonso.Universe2Crazy.entities.customization.Color;

import javax.swing.*;

public class UniversePanel extends JPanel {

    private Color backgroundColor;

    public UniversePanel(Color color) {
        this.backgroundColor = color;

        this.setBackground(color.getColorCode());
        this.setVisible(true);
    }
}
