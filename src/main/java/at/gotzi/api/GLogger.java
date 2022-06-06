package at.gotzi.api;

import at.gotzi.api.ano.Comment;

import java.io.*;
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
    public static GLogger getDefaultGotziLogger() {
        setLoggerFormat(Colors.BLUE_BRIGHT);
        return new GLogger( "Information", "Warning", "Debug", Colors.WHITE, Colors.CYAN);
    }


    /**
     * It sets the format of the logger to the format specified by the user
     *
     * @param color The color of the log message.
     * @param format The format of the log message.
     */
    public static void setLoggerFormat(String color, String format) {
        System.setProperty("java.util.logging.SimpleFormatter.format", color + format);
    }

    /**
     * > Set the format of the logger to be a color, the date, the time, the log level, and the message
     *
     * @param color The color of the timestamp.
     */
    public static void setLoggerFormat(String color) {
        System.setProperty("java.util.logging.SimpleFormatter.format", color + "[%1$tF %1$tT]" + Colors.WHITE + " %5$s %n");
    }

    /*
    get Default Formatter
     */
    public static Formatter getFormat() {
        return new Formatter() {
            @Override
            public String format(LogRecord record) {
                return Colors.removeColors(new SimpleFormatter().format(record));
            }
        };
    }

    //non-static stuff
    //text var: When you're executing method info(String) this is what text comes by default in brackets
    private final String info;
    //text var: When you're executing method warning(String) this is what text comes by default in brackets
    private final String warning;

    private final String debug;

    //text var: Color for the actual message
    private final String msgColor;
    //text var: Color for the 2 brackets that surrounds var::info
    private final String bracketColor;


    @Comment.Constructor
    public GLogger(String info, String warning, String debug, String msgColor, String bracketColor) {
        super("GotziLogger", null);
        LogManager.getLogManager().addLogger(this);

        this.debug = debug;
        this.info = info;
        this.warning = warning;
        
        this.msgColor = msgColor;
        this.bracketColor = bracketColor;
    }

    /**
     * It overrides the info function in the parent class.
     *
     * @param msg The message to log.
     */
    @Override
    public void info(String msg) {
        this.info((Object) msg);
    }

    /**
     * If the user has specified a warning handler, call it with the given message.
     *
     * @param msg The message to log.
     */
    @Override
    public void warning(String msg) {
        this.warning((Object) msg);
    }

    public void info(Object msg) {
        String s = bracketColor + "[" + Colors.GREEN + info + bracketColor + "] " +  msgColor + msg.toString() + Colors.RESET;
        super.info(s);
    }


    /**
     * It prints out the class name, the method name, and the message.
     *
     * @param msg The message to be logged
     * @param cls The class that the log is being called from.
     */
    public void info(Object msg, Class<?> cls) {
        String s = Colors.BLUE +
                "Class: " +
                cls.getCanonicalName()  + " " + bracketColor +
                "["+ Colors.GREEN + info + bracketColor + "] " +  msgColor + msg.toString() + Colors.RESET;
        super.info(s);
    }


    public void warning(Object msg) {
        String s = bracketColor + "[" +  Colors.YELLOW + warning + bracketColor + "] " +  msgColor + msg.toString() + Colors.RESET;
        super.warning(s);
    }


    /**
     * It prints a warning message to the console.
     *
     * @param msg The message to be logged
     * @param cls The class that the warning is coming from.
     */
    public void warning(Object msg, Class<?> cls) {
        String s = Colors.RED +
                "Class: " +
                cls.getCanonicalName() + " " + bracketColor +
                "["+ Colors.YELLOW + warning + bracketColor + "] " +  msgColor + msg.toString() + Colors.RESET;
        super.warning(s);
    }

    public void debug(Object msg) {
        String s = bracketColor + "[" +  Colors.YELLOW + debug + bracketColor + "] " +  msgColor + msg.toString() + Colors.RESET;
        super.warning(s);
    }


    /**
     * It prints out a debug message with the class name.
     *
     * @param msg The message to be printed
     * @param cls The class that the debug message is coming from.
     */
    public void debug(Object msg, Class<?> cls) {
        String s = Colors.RED +
                "Class: " +
                cls.getCanonicalName() + " " + bracketColor +
                "["+ Colors.YELLOW + debug + bracketColor + "] " +  msgColor + msg.toString() + Colors.RESET;
        super.warning(s);
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

    /**
     * > This function returns the value of the variable `msgColor`
     *
     * @return The msgColor variable is being returned.
     */
    @Comment.Getter
    public String getMsgColor() {
        return msgColor;
    }

    /**
     * > This function returns the bracketColor field
     *
     * @return The bracketColor variable is being returned.
     */
    @Comment.Getter
    public String getBracketColor() {
        return bracketColor;
    }
}
