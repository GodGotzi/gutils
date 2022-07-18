package at.gotzi.api.logging;

import at.gotzi.api.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

public class GDefaultFormatter extends GFormatter {

    protected GDefaultFormatter() {
    }

    private static boolean colors = true;

    @Override
    public String formatWithColors(LogRecord record) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        String threadName = Thread.currentThread().getName();
        String loggerName = record.getLoggerName();
        String message = record.getMessage();

        if (record.getLevel() instanceof GLevel level) {
            return Color.BLUE_BRIGHT + "[" + Color.PURPLE + date + Color.BLUE_BRIGHT + "] " + "[Thread -> " + Color.PURPLE + threadName + Color.BLUE_BRIGHT + "] " + "[Logger -> " + Color.PURPLE + loggerName + Color.BLUE_BRIGHT + "] " + "[" + level.color + level.getName() + Color.BLUE_BRIGHT + "] -> " + level.color + message + Color.RESET + "\n";
        }

        return Color.BLUE_BRIGHT + "[" + Color.PURPLE + date + Color.BLUE_BRIGHT + "] " + "[Thread -> " + Color.PURPLE + threadName + Color.BLUE_BRIGHT + "] " + "[Logger -> " + Color.PURPLE + loggerName + Color.BLUE_BRIGHT + "] " + "[" + record.getLevel().getName() + Color.BLUE_BRIGHT + "] -> " + message + Color.RESET + "\n";
    }

    @Override
    public String formatWithOutColors(LogRecord record) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        String threadName = Thread.currentThread().getName();
        String levelName = record.getLevel().getName();
        String loggerName = record.getLoggerName();
        String message = record.getMessage();

        return Color.removeColors("[" + date + "] " + "[Thread -> " + threadName + "] " + "[Logger -> " + loggerName + "] [" + levelName + "] -> " + message + "\n");
    }

    public static void setColorsOnOff(boolean colors) {
        GDefaultFormatter.colors = colors;
    }
}