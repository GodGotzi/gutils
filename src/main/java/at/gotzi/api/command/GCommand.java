package at.gotzi.api.command;

import at.gotzi.api.GHelper;
import at.gotzi.api.ano.Comment;
import at.gotzi.api.logging.GLevel;
import at.gotzi.api.template.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GCommand implements Executable<GCommandContext> {

    private final List<GArgument> arguments = new ArrayList<>();
    private final String label;
    private CommandAction nullAction;

    @Comment.Constructor
    public GCommand(String label) {
        this.label = label;
    }

    @Comment.Constructor
    public GCommand(String label, CommandAction nullAction) {
        this.label = label;
        this.nullAction = nullAction;
    }

    /**
     * If the command has no arguments, run the nullAction. If the command has arguments, check if the first argument is
     * valid. If it is, check if the next argument is valid. If it is, check if the next argument is valid. If it is, run
     * the command action
     *
     * @param gotziCommandContext The context of the command.
     */
    @Override
    public void execute(GCommandContext gotziCommandContext) {
        if (gotziCommandContext.args().length == 0 && nullAction != null) {
            nullAction.run(gotziCommandContext);
            return;
        }

        GArgument gArgument =
                arguments.stream().filter(arg -> filterGotziArguments(arg, gotziCommandContext))
                        .findFirst().orElse(null);

        if (gArgument == null) {
            GHelper.LOGGER.log(GLevel.Info, gotziCommandContext.properties().getProperty("onFalseSyntax"),
                    gotziCommandContext.cmd());
            return;
        }

        for (int i = 0; i < gotziCommandContext.args().length-1; i++) {
            gArgument = getNextArgument(gArgument, gotziCommandContext);
            if (gArgument == null) {
                GHelper.LOGGER.log(GLevel.Info, gotziCommandContext.properties().getProperty("onFalseSyntax"),
                        gotziCommandContext.cmd());
                break;
            }

            if (gArgument.getIndex() == (gotziCommandContext.args().length-1) && gArgument.getCommandAction() != null) {
                gArgument.getCommandAction().run(gotziCommandContext);
            }
        }
    }

    public int tabComplete(String buffer, int cursor, List<CharSequence> candidates) {


        //todo


        return 0;
    }

    /**
     * It returns the next argument in the command chain
     *
     * @param gArgument The current argument that is being processed.
     * @param gotziCommandContext The context of the command.
     * @return The next argument in the command.
     */
    private GArgument getNextArgument(GArgument gArgument, GCommandContext gotziCommandContext) {
        if (gArgument.getSubCommands() == null) return null;
        return Arrays.stream(gArgument.getSubCommands()).filter(arg -> filterGotziArguments(gArgument, gotziCommandContext)).findFirst().orElse(null);
    }

    /**
     * "If the argument's index is less than the number of arguments in the command context, and the argument's label is
     * equal to the argument at the index in the command context, or the argument is a value argument, return true."
     *
     * The first part of the function checks if the argument's index is less than the number of arguments in the command
     * context. This is to make sure that the argument is not out of bounds
     *
     * @param gArgument The argument that is being checked.
     * @param gotziCommandContext The context of the command.
     * @return A boolean value.
     */
    private boolean filterGotziArguments(GArgument gArgument, GCommandContext gotziCommandContext) {
        return gArgument.getIndex() < gotziCommandContext.args().length &&
                (gArgument.getLabel().equals(gotziCommandContext.args()[gArgument.getIndex()])
                        || gArgument instanceof GArgumentValue);
    }

    /**
     * If the argument doesn't already exist, add it to the list
     *
     * @param gArgument The argument to add to the list of arguments.
     */
    public void addArgument(GArgument gArgument) {
        if (arguments.stream().noneMatch(argument -> argument.getLabel().equals(gArgument.getLabel())))
            arguments.add(gArgument);
    }

    @Comment.Getter
    public String getLabel() {
        return label;
    }

    @Comment.Getter
    public List<GArgument> getArguments() {
        return arguments;
    }
}