package at.gotzi.api.logging;

import at.gotzi.api.Color;

import java.util.logging.Level;

public class GLevel extends Level {

    public static final GLevel Error = new GLevel("Error", Color.RED, 950);
    public static final GLevel Info = new GLevel("Information", Color.WHITE, 951);
    public static final GLevel Debug = new GLevel("Debug", Color.CYAN, 952);
    public static final GLevel Warning = new GLevel("Warning", Color.YELLOW, 953);

    public String color;

    protected GLevel(String name, String color, int value) {
        super(color + name + color, value);

        this.color = color;
    }
}
