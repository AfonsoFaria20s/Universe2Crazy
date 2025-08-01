package org.afonso.Universe2Crazy.console;

import org.afonso.Universe2Crazy.entities.UniverseManager;

import javax.swing.*;
import java.awt.*;

public class Console extends JFrame {

    private HistoryPanel historyPanel;
    private CommandLine commandLine;
    private UniverseManager universeManager;

    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem clearConsole;

    public Console() {
        this.setSize(400,250);
        this.setTitle("Console");
        this.setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth()-150,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setLayout(new BorderLayout());

        historyPanel = new HistoryPanel();
        commandLine = new CommandLine();

        menuBar = new JMenuBar();
        menu1 = new JMenu("Tools");
        clearConsole = new JMenuItem("Clear Console");

        menuBar.add(menu1);
        menu1.add(clearConsole);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(historyPanel, BorderLayout.CENTER);
        this.add(commandLine, BorderLayout.SOUTH);

        UniverseManager universeManager = new UniverseManager(historyPanel);
        ConsoleManager consoleManager = new ConsoleManager(historyPanel, commandLine, menuBar, universeManager, this);
    }

    public void init() {
        this.setVisible(true);
    }
}
