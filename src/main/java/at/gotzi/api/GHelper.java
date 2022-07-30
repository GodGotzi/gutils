package at.gotzi.api;

import at.gotzi.api.logging.GLevel;
import at.gotzi.api.logging.GLogger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class GHelper {

    public static GLogger LOGGER;

    public static String getCallerClassName() {
        return new Exception().getStackTrace()[2].getClassName();
    }

    public static String getCallerClassName(int index) {
        return new Exception().getStackTrace()[index].getClassName();
    }

    public static String getCallerMethodName() {
        return new Exception().getStackTrace()[2].getMethodName();
    }

    public static String getCallerMethodName(int index) {
        return new Exception().getStackTrace()[index].getMethodName();
    }

    public static String getFormattedTime() {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .appendValue(ChronoField.HOUR_OF_DAY)
                .appendLiteral(":")
                .appendValue(ChronoField.MINUTE_OF_HOUR)
                .appendLiteral(":")
                .appendValue(ChronoField.SECOND_OF_MINUTE).toFormatter();
        return LocalDateTime.now().format(df);
    }

    public static String getFormattedTime(ZoneId zoneId) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .appendValue(ChronoField.HOUR_OF_DAY)
                .appendLiteral(":")
                .appendValue(ChronoField.MINUTE_OF_HOUR)
                .appendLiteral(":")
                .appendValue(ChronoField.SECOND_OF_MINUTE).toFormatter();
        return LocalDateTime.now(zoneId).format(df);
    }

    public static String getFormattedTime(DateTimeFormatter df, ZoneId zoneId) {
        return LocalDateTime.now(zoneId).format(df);
    }

    public static String getFormattedTime(DateTimeFormatter df) {
        return LocalDateTime.now(ZoneId.of("/Vienna")).format(df);
    }

    public static void debugMessage(String s) {
        String cls = GHelper.getCallerClassName().equals(GHelper.class.getCanonicalName()) ?
                getCallerClassName(3) : getCallerClassName();
        try {
            LOGGER.log(GLevel.Debug, s + "Class:" + Class.forName(cls));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void debugMessage(String s, int number) {
        debugMessage(s + "| debug-number: " + number);
    }

    public static boolean initFile(String file) {
        try {
            return new File(file).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean initDir(String file) {
        return new File(file).mkdirs();
    }

}