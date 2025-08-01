package org.afonso.Universe2Crazy.entities;

import javax.swing.*;
import java.awt.*;

public class UniverseFrame extends JFrame {

    private Dimension defaultSize;

    private JPanel panel;
    private String title;

    public UniverseFrame(String title, JPanel panel) {
        this.title = title;
        this.panel = panel;

        this.defaultSize = new Dimension(350,350);

        this.setTitle(this.title);
        this.setSize(defaultSize);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(panel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateFrame(String title) {
        this.setTitle(title);
    }
}
