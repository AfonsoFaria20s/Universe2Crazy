package org.afonso.Universe2Crazy.entities;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UniverseFrame extends JFrame {

    private Dimension defaultSize;

    private UniversePanel panel;
    private String title;

    public UniverseFrame(String title, UniversePanel panel) {
        this.title = title;
        this.panel = panel;

        this.defaultSize = new Dimension(350,350);

        this.setTitle(this.title);
        this.setSize(defaultSize);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(panel);
    }

    public UniversePanel getPanel() {
        return panel;
    }

    public void updateFrame(String title) {
        this.setTitle(title);
    }

    public void explode() {
        Timer timer = new Timer(50,null);
        int[] count = {0};

        timer.addActionListener(e -> {
            int xOffset = (int)(Math.random() * 15 - 5);
            int yOffset = (int)(Math.random() * 15 - 5);
            Point newPosition = new Point(this.getX()+xOffset, this.getY()+yOffset);
            this.setLocation(newPosition);

            if(++count[0]>15) {
                timer.stop();
                this.dispose();
            }
        });

        timer.start();
    }
}
