package at.gotzi.api.test;

import at.gotzi.api.GLogger;
import at.gotzi.api.command.GArgument;
import at.gotzi.api.command.GCommand;

public class TestCommand extends GCommand {

    public TestCommand(GLogger gLogger) {
        super("test", gotziCommandContext -> gLogger.info("test command got executed"));
        this.build(gLogger);
    }

    /**
     * It adds a new argument to the command.
     *
     * @param gLogger The logger to use for logging.
     */
    private void build(GLogger gLogger) {
        addArgument(new GArgument("test2", 0,
                new GArgument[]{
                        new GArgument("test3.25", 1,
                                new GArgument[]{}, gotziCommandContext -> gLogger.info("test3.25 arg got executed")),
                        new GArgument("test3.5", 1,
                                new GArgument[]{}, gotziCommandContext -> gLogger.info("test3.5 arg got executed"))},
                gotziCommandContext -> gLogger.info("test arg got executed")));
    }

}
