package org.afonso.Universe2Crazy.console;

public class ParsedCommand {
    private String command;
    private String[] args;

    public ParsedCommand(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
