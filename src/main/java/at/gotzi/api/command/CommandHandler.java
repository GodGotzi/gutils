package at.gotzi.api.command;

import at.gotzi.api.GHelper;
import at.gotzi.api.logging.GLevel;
import jline.console.completer.Completer;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CommandHandler implements Completer {

    private final Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    private char commandChar;
    private final Map<String, GCommand> commandMap = new LinkedHashMap<>();

    public CommandHandler(char commandChar) {
        this.commandChar = commandChar;

        InputStream in = CommandHandler.class.getClassLoader().getResourceAsStream("command-handler.properties");
        if (in == null)
            in = CommandHandler.class.getClassLoader().getResourceAsStream("default-command-handler.properties");

        try {
            synchronized (properties) {
                properties.load(in);
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        }
    }

    /**
     * This function will run the executeCommand function every tick, and will pass the scanner's scan() function as the
     * command, and an empty object array as the arguments.
     *
     * @param commandScanner The scanner to use.
     */
    public void scanLoop(CommandScanner commandScanner) {
        while(true) {
            this.executeCommand(commandScanner.scan());
        }
    }

    /**
     * It takes a GCommand object and adds it to the commandMap HashMap
     *
     * @param gCommand The command you want to register.
     */
    public synchronized void registerCommand(GCommand gCommand) {
        commandMap.put(gCommand.getLabel(), gCommand);
    }

    /**
     * It takes a string, splits it into a command and arguments, and then executes the command with the arguments
     *
     * @param line The line of text that was sent to the bot.
     */
    public synchronized void executeCommand(String line) {
        if (line.charAt(0) == commandChar || commandChar == ' ') {
            if (commandChar != ' ') line = line.substring(1);
            String[] cmdSplit = line.split(" ", 2);
            if (cmdSplit.length < 2) executeCommand(cmdSplit[0], new String[]{});
            else executeCommand(cmdSplit[0], cmdSplit[1].split(" "));
        }
    }

    /**
     * It executes a command
     *
     * @param cmd The command name
     * @param args The arguments of the command.
     */
    public synchronized void executeCommand(String cmd, String[] args) {
        if (commandMap.get(cmd) == null) {
            GHelper.LOGGER.log(GLevel.Info, properties.getProperty("commandNotExists"), cmd);
            return;
        }
        commandMap.get(cmd).execute(new GCommandContext(cmd, args, properties));
    }

    @Override
    public int complete(String s, int i, List<CharSequence> list) {
        GHelper.LOGGER.log(GLevel.Debug, "test");
        return 0;
    }

    public synchronized void setCommandChar(char commandChar) {
        this.commandChar = commandChar;
    }
}
