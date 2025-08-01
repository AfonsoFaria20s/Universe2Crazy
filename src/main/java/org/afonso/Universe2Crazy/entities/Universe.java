package org.afonso.Universe2Crazy.entities;

import javax.swing.*;
import java.awt.*;

public class Universe {
    private String name;
    private Color bgColor;

    public UniverseFrame universeFrame;

    public Universe(String name, UniverseFrame universeFrame) {
        this.name = name;
        this.universeFrame = universeFrame;

    }

    // public Universe(String name, Color bgColor, JFrame universeFrame) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UniverseFrame getUniverseFrame() {
        return universeFrame;
    }

    public void setUniverseFrame(UniverseFrame frame) {
        this.universeFrame = frame;
    }
}
