package at.gotzi.api.command;

import at.gotzi.api.Action;
import at.gotzi.api.Colors;
import at.gotzi.api.GHelper;
import at.gotzi.api.GotziRunnable;
import at.gotzi.api.template.Scanner;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    static {
        onFalseSyntax = s -> GHelper.LOGGER.info(Colors.RED + "FalseSyntax on Command \"" + s + "\"");
    }

    static {
        commandNotExists = s -> GHelper.LOGGER.info("Command not found: " + s);
    }

    public static Action<String> onFalseSyntax;
    public static Action<String> commandNotExists;

    private final char commandChar;
    private final Map<String, GCommand> commandMap = new HashMap<>();

    public CommandHandler(char commandChar) {
        this.commandChar = commandChar;
    }

    /**
     * This function will run the executeCommand function every tick, and will pass the scanner's scan() function as the
     * command, and an empty object array as the arguments.
     *
     * @param scanner The scanner to use.
     * @return The CommandHandler itself.
     */
    public CommandHandler scanLoop(Scanner scanner) {
        new GotziRunnable() {
            @Override
            public void run() {
                executeCommand(scanner.scan(), new Object[]{});
            }
        }.runRepeatingTaskAsync(1);
        return this;
    }

    /**
     * It takes a GCommand object and adds it to the commandMap HashMap
     *
     * @param gCommand The command you want to register.
     */
    public void registerCommand(GCommand gCommand) {
        commandMap.put(gCommand.getLabel(), gCommand);
    }

    /**
     * It takes a string, splits it into a command and arguments, and then executes the command with the arguments
     *
     * @param line The line of text that was sent to the bot.
     * @param objects An array of objects that can be used by the command.
     */
    public void executeCommand(String line, Object[] objects) {
        if (line.charAt(0) == commandChar || commandChar == ' ') {
            if (commandChar != ' ') line = line.substring(1);
            String[] cmdSplit = line.split(" ", 2);
            if (cmdSplit.length < 2) executeCommand(cmdSplit[0], new String[]{}, objects);
            else executeCommand(cmdSplit[0], cmdSplit[1].split(" "), objects);
        }
    }

    /**
     * It executes a command
     *
     * @param cmd The command name
     * @param args The arguments of the command.
     * @param objects An array of objects that can be passed to the command.
     */
    public synchronized void executeCommand(String cmd, String[] args, Object[] objects) {
        if (commandMap.get(cmd) == null) {
            commandNotExists.run(cmd);
            return;
        }
        commandMap.get(cmd).execute(new GCommandContext(cmd, args, objects));
    }
}
