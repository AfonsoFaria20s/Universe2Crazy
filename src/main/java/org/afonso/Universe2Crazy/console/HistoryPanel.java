package org.afonso.Universe2Crazy.console;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextArea historyArea;

    public HistoryPanel() {
        this.setLayout(new BorderLayout());

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(historyArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Log the command
    public void appendCommandHistory(String text) {
        historyArea.append("> " + text + "\n");
    }

    // Log a comment
    public void appendCommentHistory(String text) {
        historyArea.append(text + "\n");
    }

    public void clearHistory() {
        historyArea.setText("");
    }
}
