package at.gotzi.api.command;

import at.gotzi.api.GHelper;
import at.gotzi.api.GotziRunnable;
import at.gotzi.api.logging.GLevel;
import jline.console.completer.Completer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CommandHandler implements Completer {

    private static final Properties properties = new Properties();

    static {
        InputStream in = CommandHandler.class.getClassLoader().getResourceAsStream("command-handler.properties");
        if (in == null)
            in = CommandHandler.class.getClassLoader().getResourceAsStream("default-command-handler.properties");

        try {
            properties.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    private final char commandChar;
    private final Map<String, GCommand> commandMap = new HashMap<>();

    public CommandHandler(CommandScanner commandScanner, char commandChar) {
        this.commandChar = commandChar;
        this.scanLoop(commandScanner);
    }

    /**
     * This function will run the executeCommand function every tick, and will pass the scanner's scan() function as the
     * command, and an empty object array as the arguments.
     *
     * @param commandScanner The scanner to use.
     */
    private void scanLoop(CommandScanner commandScanner) {
        new GotziRunnable() {
            @Override
            public void run() {
                executeCommand(commandScanner.scan(), new Object[]{});
            }
        }.runRepeatingTaskAsync(-1);
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
            GHelper.LOGGER.log(GLevel.Info, properties.getProperty("commandNotExists"), cmd);
            return;
        }
        commandMap.get(cmd).execute(new GCommandContext(cmd, args, objects, properties));
    }

    @Override
    public int complete(String s, int i, List<CharSequence> list) {
        GHelper.LOGGER.log(GLevel.Debug, "test");
        return 0;
    }
}
