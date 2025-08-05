package org.afonso.Universe2Crazy.console;

import org.afonso.Universe2Crazy.entities.UniverseManager;
import org.afonso.Universe2Crazy.entities.customization.Colors;
import org.afonso.Universe2Crazy.session.UserSession;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;

public class Console extends JFrame {

    private HistoryPanel historyPanel;
    private CommandLine commandLine;
    private UniverseManager universeManager;
    private UserSession userSession;
    private Colors colors;

    private JMenuBar menuBar;

    private JMenu menu1;
    private JMenu menu2;

    private JMenuItem clearConsole;
    private JMenuItem exit;

    public Console() {
        this.setSize(320,280);
        this.setTitle("Console");
        this.setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width-this.getWidth(),
                Toolkit.getDefaultToolkit().getScreenSize().height/2-this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_VERT);

        this.setLayout(new BorderLayout());

        historyPanel = new HistoryPanel();
        commandLine = new CommandLine();

        menuBar = new JMenuBar();

        menu1 = new JMenu("General");
        menu2 = new JMenu("Tools");

        clearConsole = new JMenuItem("Clear Console");
        exit = new JMenuItem("Exit");

        menuBar.add(menu1);
        menuBar.add(menu2);

        menu1.add(exit);

        menu2.add(clearConsole);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(historyPanel, BorderLayout.CENTER);
        this.add(commandLine, BorderLayout.SOUTH);

        colors = new Colors();
        userSession = new UserSession();

        UniverseManager universeManager = new UniverseManager(historyPanel);
        ConsoleManager consoleManager = new ConsoleManager(historyPanel, commandLine, menuBar, universeManager, this, colors, userSession);
    }

    public void init() {
        this.setVisible(true);
    }
}
