package org.afonso.Universe2Crazy.console;

import org.afonso.Universe2Crazy.entities.Universe;
import org.afonso.Universe2Crazy.entities.UniverseFrame;
import org.afonso.Universe2Crazy.entities.UniverseManager;
import org.afonso.Universe2Crazy.entities.UniversePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleManager {

    private HistoryPanel historyPanel;
    private CommandLine commandLine;
    private UniverseManager universeManager;
    private CommandParser commandParser;
    private Console console;

    private JMenuBar menuBar;

    public ConsoleManager(HistoryPanel historyPanel, CommandLine commandLine, JMenuBar menuBar, UniverseManager universeManager, Console console) {
        this.historyPanel = historyPanel;
        this.commandLine = commandLine;
        this.menuBar = menuBar;
        this.universeManager = universeManager;
        this.console = console;

        this.commandParser = new CommandParser();

        setupListeners();
    }

    public void setupListeners() {
        commandLine.getField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {

                    processInput();

                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {}
        });

        menuBar.getMenu(0).getItem(0).addActionListener(actionEvent -> historyPanel.clearHistory());
    }

    public void processInput() {
        historyPanel.appendCommandHistory(commandLine.getCommand());
        executeCommand(commandLine.getCommand());
    }

    public void executeCommand(String input) {
        ParsedCommand parsedCommand = commandParser.parse(input);
        if(parsedCommand.getArgs().length==0) {
            historyPanel.appendCommentHistory("");
            return;
        }

        String command = parsedCommand.getCommand();
        String[] args = parsedCommand.getArgs();

        switch (command) {
            case "create":
                String universeName = parsedCommand.getArgs()[0];
                universeManager.addUniverse(new Universe(universeName, new UniverseFrame(universeName, new UniversePanel())), universeName);
                historyPanel.appendCommentHistory("Created Universe: "+universeName);
                break;
            case "list":
                if(args[0].equals("universes")) {
                    historyPanel.appendCommentHistory(universeManager.getListedUniverses());
                }
                break;
            case "setname":
                String id = args[0];
                String newName = args[1];

                universeManager.getUniverses().get(id).setName(newName);
                universeManager.getUniverses().get(id).getUniverseFrame().updateFrame(newName);
                break;
            default:
                break;
        }
    }
}
