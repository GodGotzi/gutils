package at.gotzi.api.test;

import at.gotzi.api.logging.GFormatter;
import at.gotzi.api.logging.GLogger;

public class Main {

    public static void main(String[] args) {
        GLogger logger = GLogger.getDefaultGotziLogger("main", true);
        logger.error("test");
        GFormatter.setColorsOnOff(false);
        logger.error("ey");
    }
}
