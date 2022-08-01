package at.gotzi.api.template.logging;

import at.gotzi.api.Color;
import at.gotzi.api.GHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

public class GDefaultFormatter extends GFormatter {

    public GDefaultFormatter(boolean colors) {
        super(colors);
    }

    @Override
    public String formatWithColors(LogRecord record) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        String threadName = Thread.currentThread().getName();
        String loggerName = record.getLoggerName();
        String message = record.getMessage();

        if (record.getLevel() instanceof GLevel level) {
            return Color.BLUE_BRIGHT + "[" + Color.RED + date + Color.BLUE_BRIGHT + "] " + "[Thread -> " + Color.RED + threadName + Color.BLUE_BRIGHT + "] " + "[Logger -> " + Color.RED + loggerName + Color.BLUE_BRIGHT + "] " + "[" + level.color + level.getName() + Color.BLUE_BRIGHT + "] -> " + level.color + message + Color.RESET + "\n";
        }

        return Color.BLUE_BRIGHT + "[" + Color.RED + date + Color.BLUE_BRIGHT + "] " + "[Thread -> " + Color.RED + threadName + Color.BLUE_BRIGHT + "] " + "[Logger -> " + Color.RED + loggerName + Color.BLUE_BRIGHT + "] " + "[" + record.getLevel().getName() + Color.BLUE_BRIGHT + "] -> " + message + Color.RESET + "\n";
    }

    @Override
    public String formatWithOutColors(LogRecord record) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        String threadName = Thread.currentThread().getName();
        String levelName = record.getLevel().getName();
        String loggerName = record.getLoggerName();
        String message = record.getMessage();

        return Color.removeColors("[" + date + "] " + "[Class -> " + threadName + "] " + "[Logger -> " + loggerName + "] [" + levelName + "] -> " + message + "\n");
    }
}