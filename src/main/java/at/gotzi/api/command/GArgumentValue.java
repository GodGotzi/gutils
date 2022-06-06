package at.gotzi.api.command;

public class GArgumentValue extends GArgument {

    public GArgumentValue(int index, GArgument[] subArguments, CommandAction commandAction) {
        super("", index, subArguments, commandAction);
    }

    public GArgumentValue(int index, CommandAction commandAction) {
        super("", index, commandAction);
    }
}
