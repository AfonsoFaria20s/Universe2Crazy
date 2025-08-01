package org.afonso.Universe2Crazy.console;

import javax.swing.*;
import java.awt.*;

public class CommandLine extends JPanel {
    private JTextField commandField;

    public CommandLine() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 30));

        JLabel prefix = new JLabel("> ");
        prefix.setFont(new Font("Monospaced", Font.PLAIN, 12));
        prefix.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        commandField = new JTextField();
        commandField.setFont(new Font("Monospaced", Font.PLAIN, 12));
        commandField.setBorder(null);
        commandField.setBackground(new Color(0, 0, 0, 0));
        commandField.setOpaque(false);

        this.add(prefix, BorderLayout.WEST);
        this.add(commandField, BorderLayout.CENTER);
    }

    public String getCommand() {
        return commandField.getText();
    }

    public void clear() {
        commandField.setText("");
    }

    public JTextField getField() {
        return commandField;
    }
}
