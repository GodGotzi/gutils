package at.gotzi.api.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public abstract class GFormatter extends Formatter {

    private static boolean colors = true;

    public static void setColorsOnOff(boolean colors) {
        GFormatter.colors = colors;
    }

    @Override
    public String format(LogRecord record) {
        if (colors) return formatWithColors(record);
        return formatWithOutColors(record);
    }

    public abstract String formatWithColors(LogRecord logRecord);

    public abstract String formatWithOutColors(LogRecord logRecord);
}
