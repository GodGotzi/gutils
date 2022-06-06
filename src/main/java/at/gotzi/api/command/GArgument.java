package at.gotzi.api.command;

import at.gotzi.api.ano.Comment;

public class GArgument {

    private GArgument[] subArguments;
    private final String label;
    private final int index;
    private CommandAction commandAction;

    @Comment.Constructor
    public GArgument(String label, int index, GArgument[] subArguments, CommandAction commandAction) {
        this.label = label;
        this.index = index;
        this.subArguments = subArguments;
        this.commandAction = commandAction;
    }

    @Comment.Constructor
    public GArgument(String label, int index, CommandAction commandAction) {
        this.label = label;
        this.index = index;
        this.commandAction = commandAction;
    }

    @Comment.Setter
    public void setSubCommands(GArgument[] subArguments) {
        this.subArguments = subArguments;
    }

    @Comment.Setter
    public void setCommandAction(CommandAction commandAction) {
        this.commandAction = commandAction;
    }

    @Comment.Getter
    public String getLabel() {
        return label;
    }

    @Comment.Getter
    public int getIndex() {
        return index;
    }

    @Comment.Getter
    public GArgument[] getSubCommands() {
        return subArguments;
    }

    @Comment.Getter
    public CommandAction getCommandAction() {
        return commandAction;
    }
}
