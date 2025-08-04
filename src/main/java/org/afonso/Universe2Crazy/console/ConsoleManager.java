package org.afonso.Universe2Crazy.console;

import org.afonso.Universe2Crazy.entities.Universe;
import org.afonso.Universe2Crazy.entities.UniverseFrame;
import org.afonso.Universe2Crazy.entities.UniverseManager;
import org.afonso.Universe2Crazy.entities.UniversePanel;
import org.afonso.Universe2Crazy.entities.customization.Colors;
import org.afonso.Universe2Crazy.session.UserSession;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleManager {

    private HistoryPanel historyPanel;
    private CommandLine commandLine;
    private UniverseManager universeManager;
    private CommandParser commandParser;
    private Console console;
    private Colors colors;
    private UserSession userSession;

    private JMenuBar menuBar;

    public ConsoleManager(HistoryPanel historyPanel,
                          CommandLine commandLine,
                          JMenuBar menuBar,
                          UniverseManager universeManager,
                          Console console,
                          Colors colors,
                          UserSession userSession) {
        this.historyPanel = historyPanel;
        this.commandLine = commandLine;
        this.menuBar = menuBar;
        this.universeManager = universeManager;
        this.console = console;
        this.colors = colors;
        this.userSession = userSession;

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

        menuBar.getMenu(0).getItem(0).addActionListener(actionEvent -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to leave?",
                    "",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        menuBar.getMenu(1).getItem(0).addActionListener(actionEvent -> historyPanel.clearHistory());
    }

    public void processInput() {
        historyPanel.appendCommandHistory(commandLine.getCommand());
        executeCommand(commandLine.getCommand());
    }

    public void executeCommand(String input) {
        ParsedCommand parsedCommand = commandParser.parse(input);
        if(parsedCommand.getCommand().isEmpty()) {
            comment("");
            return;
        }

        String command = parsedCommand.getCommand();
        String[] args = parsedCommand.getArgs();

        // Base cases
        switch (command) {
            case "create":
                String universeNameToCreate = parsedCommand.getArgs()[0];
                universeManager.addUniverse(
                        new Universe(
                                universeNameToCreate,
                                new UniverseFrame(
                                        universeNameToCreate,
                                        new UniversePanel(
                                                colors,
                                                colors.getRandomColorFromMap()))),
                        universeNameToCreate);
                comment("Created Universe: " + universeNameToCreate);
                break;
            case "remove":
                String universeNameToRemove = parsedCommand.getArgs()[0];
                if (universeManager.removeUniverse(universeNameToRemove) == 0) {
                    comment("Removed Universe: " + universeNameToRemove);
                } else {
                    comment("Error: Universe does not exist!");
                }
                break;
            case "list":
                if (args[0].equals("universes")) {
                    comment(universeManager.getListedUniverses());
                } else if (args[0].equals("colors")) {
                    comment(colors.getListedColors());
                }
                break;
            case "name":
                String id = args[0];
                StringBuilder sb = new StringBuilder();

                for(String arg : args) {
                    sb.append(arg).append(" ");
                }

                String newName = sb.toString();

                universeManager.getUniverses().get(id).setName(newName);
                universeManager.getUniverses().get(id).getUniverseFrame().updateFrame(newName);
                break;
            case "color":
                String universeName = args[0];
                String newColor = args[1];
                universeManager.getUniverses().get(universeName).getUniverseFrame().getPanel().changeBackgroundColor(newColor);
                comment("Changed background color!");
                break;
            case "clear":
                historyPanel.clearHistory();
                break;

            // Easter egg cases
            case "godmode":
                comment("Your touch rewrites fate.\nBut even gods fall in time...");
                userSession.enableGodMode();

                // Auto-disable after 1 minute (60_000 ms)
                new Thread(() -> {
                    try {
                        Thread.sleep(60_000);
                        userSession.disableGodMode();
                        comment("GODMODE has faded...\nThe stars watch once more.");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
                break;
            case "explode":
                if(userSession.isGodModeEnabled()) {
                    String universeId = parsedCommand.getArgs()[0];
                    if (universeManager.explodeUniverse(universeId) == 0) {
                        comment("The one who chose this path\n shall receive the the things he wished for...");
                    } else {
                        comment("Error: Universe does not exist!");
                    }
                } else {
                    comment("Once the stars align,\ngreat power shall be used....");
                }
                break;
            case "bigbang":
                if(userSession.isGodModeEnabled()) {
                    comment("Let the Almighty know true fear!");
                    universeManager.initiateBigBang();
                } else {
                    comment("Forbidden: Only those who walk among gods\n may unleash the great power!");
                }
                break;
            default:
                comment("Unknown Command: ");
                StringBuilder sb = new StringBuilder();
                sb.append(" - ").append(command);
                for (String arg : args) {
                    sb.append(" ").append(arg);
                }
                comment(sb.toString());
                break;
        }

        // Clear the command line for a new command
        commandLine.clear();
    }

    private void comment(String text) {
        historyPanel.appendCommentHistory(text);
    }
}
