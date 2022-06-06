package at.gotzi.api.test;

import at.gotzi.api.*;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        GHelper.LOGGER.setLogSaveForFile(new GFile("test.txt"), GLogger.getFormat(), new String[]{"nice", "nice", "nice", "nice", "nice"});
        GLogger gLogger = GHelper.LOGGER;

        gLogger.info("Hi");
        gLogger.info("Hi2");
        gLogger.info("Hi3");
        gLogger.info("Hi4");

        GCustomBigTextBuilder gCustomBigTextBuilder = new GCustomBigTextBuilder(("Was willst du von mir! asdas").toCharArray());
        gCustomBigTextBuilder.setColor(Colors.RAINBOW);
        System.out.println(gCustomBigTextBuilder.getResult());
    }
}