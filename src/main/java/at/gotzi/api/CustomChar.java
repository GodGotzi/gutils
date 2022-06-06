package at.gotzi.api;

import java.util.Arrays;

public class CustomChar {

    private final String color;
    private final String[] lines;

    public CustomChar(String[] lines, String color) {
        this.color = color;
        this.lines = new String[lines.length];
        build(lines);
    }

    /**
     * It takes an array of strings and adds a color to each string
     *
     * @param lines The lines of the sign.
     */
    private void build(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            this.lines[i] = color + lines[i];
        }
    }

    /**
     * This function returns the lines of the file.
     *
     * @return The lines array is being returned.
     */
    public String[] getLines() {
        return lines;
    }
}
