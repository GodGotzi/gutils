package at.gotzi.api.template.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public abstract class GFormatter extends Formatter {

    private boolean colors = true;

    public GFormatter(boolean colors) {
        this.colors = colors;
    }

    @Override
    public String format(LogRecord record) {
        if (this.colors) return formatWithColors(record);
        return formatWithOutColors(record);
    }

    public abstract String formatWithColors(LogRecord logRecord);

    public abstract String formatWithOutColors(LogRecord logRecord);
}
