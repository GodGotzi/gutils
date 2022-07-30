package at.gotzi.api.logging;

import at.gotzi.api.Color;
import at.gotzi.api.ano.Comment;

import java.io.*;
import java.text.MessageFormat;
import java.util.logging.*;

public class GLogger extends Logger {

    /**
     * It returns a new GLogger object with the following parameters:
     *         - Information: The information prefix
     *         - Warning: The warning prefix
     *         - Debug: The debug prefix
     *         - Colors.WHITE: The information color
     *         - Colors.CYAN: The warning color
     *
     * @return A new GLogger object with the following parameters:
     *         - Information
     *         - Warning
     *         - Debug
     *         - Colors.WHITE
     *         - Colors.CYAN
     */
    public static GLogger getDefaultGotziLogger(String name, boolean consoleLogging, boolean consoleColors) {
        GLogger gLogger = new GLogger(name);
        if (consoleLogging) {
            StreamHandler streamHandler = new ConsoleHandler();
            streamHandler.setFormatter(new GDefaultFormatter(consoleColors));
            gLogger.addHandler(streamHandler);
        }

        return gLogger;
    }

    /*
            get Default Formatter
             */
    public static Formatter getFormat() {
        return new Formatter() {
            @Override
            public String format(LogRecord record) {
                return Color.removeColors(new SimpleFormatter().format(record));
            }
        };
    }

    String name;
    //non-static stuff
    //text var: When you're executing method info(String) this is what text comes by default in brackets


    @Comment.Constructor
    public GLogger(String name) {
        super(name, null);
        setUseParentHandlers(false);
        LogManager.getLogManager().addLogger(this);
    }

    @Override
    public void log(Level level, String msg, Object[] params) {
        MessageFormat messageFormat = new MessageFormat(msg);
        super.log(level, messageFormat.format(params));
    }

    @Override
    public void log(Level level, String msg, Object param1) {
        MessageFormat messageFormat = new MessageFormat(msg);
        super.log(level, messageFormat.format(new Object[]{param1}));
    }

    /**
     * It creates a new file, adds a new handler to the logger, and writes the infoHeader to the file
     *
     * @param file The file to save the log to.
     * @param formatter The formatter to use for the log.
     * @param infoHeader An array of strings that will be written to the file before any log messages.
     */
    public void setLogSaveForFile(File file, Formatter formatter, String[] infoHeader) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        addHandler(new StreamHandler(fileOutputStream, formatter));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        for (String line : infoHeader) {
            outputStreamWriter.append(line).append("\n");
        }
        outputStreamWriter.flush();
    }
}
