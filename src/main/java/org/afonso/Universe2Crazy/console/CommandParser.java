package org.afonso.Universe2Crazy.console;

public class CommandParser {
    public ParsedCommand parse(String input) {
        if(input==null || input.trim().isEmpty()) {
            return new ParsedCommand("", new String[0]);
        }

        String[] fullArgs = input.trim().split("\\s+");
        String command = fullArgs[0];
        String[] args = new String[fullArgs.length-1];

        System.arraycopy(fullArgs, 1, args, 0, args.length);

        return new ParsedCommand(command, args);
    }
}
