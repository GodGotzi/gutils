package at.gotzi.api;

import at.gotzi.api.template.IBuilder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GCustomBigTextBuilder extends IBuilder<String> { 

    public static int size = 1;
    private final char[] charToFormat;
    private String color = "";

    public GCustomBigTextBuilder(char[] charToFormat) {
        this.charToFormat = charToFormat;
    }

    public GCustomBigTextBuilder(String charToFormat) {
        this(charToFormat.toCharArray());
    }

    public void setColor(String color) {
        this.color = color;
    }


    /**
     * > This function takes a string and a color, and returns a string of the same characters, but in the specified color
     *
     * @return A string of the formatted text.
     */
    @Override
    public String getResult() {
        List<String[]> list = new LinkedList<>();
        StringBuilder str = new StringBuilder();
        Queue<String> colorQueue = null;

        if (this.color.equals("RAINBOW"))
            colorQueue = new LinkedList<>(Arrays.asList(Color.colorArray));

        for (char c : charToFormat) {
            if (colorQueue != null) {
                if (colorQueue.isEmpty())
                    colorQueue = new LinkedList<>(Arrays.asList(Color.colorArray));
                this.color = colorQueue.poll();
            }

            CustomChar customChar = buildChar(c, color);
            list.add(computeSize(customChar.getLines()));
        }

        for (int i = 0; i < 4;i++) {
            for (String[] s : list) {
                str.append(s[i]);
            }
            str.append("\n");
        }
        
        setSuccessful(true);
        return str.toString();
    }

    /**
     * It takes an array of strings, and returns an array of strings where each string has been escaped a number of times
     *
     * @param strArray The array of strings to be processed.
     * @return The method is returning a new array of strings.
     */
    private String[] computeSize(String[] strArray) {
        String[] newStrArray = new String[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            newStrArray[i] = strArray[i];
            for (int j = 0; j < size; j++) {
                newStrArray[i] = newStrArray[i].replace("\\", "\\\\").replace("/", "//").replace("|", "||");
            }
        }

        return newStrArray;
    }
    

    /**
     * It takes a character and a color, and returns a CustomChar object that represents that character in the given color
     *
     * @param c The character to be built
     * @param color The color of the text.
     * @return A CustomChar object
     */
    private CustomChar buildChar(char c, String color) {
        return switch (Character.toUpperCase(c)) {
            case 'A' -> new CustomChar(new String[]{
                    "   /\\    ",
                    "  /  \\   ",
                    " /----\\  ",
                    "/      \\ "
            }, color);
            case 'B' -> new CustomChar(new String[]{
                    "|---\\ ",
                    "|---/ ",
                    "|---\\ ",
                    "|___/ "
            }, color);
            case 'C' -> new CustomChar(new String[]{
                    " /--- ",
                    "/     ",
                    "\\     ",
                    " \\--- "
            }, color);
            case 'D' -> new CustomChar(new String[]{
                    "|--\\  ",
                    "|   | ",
                    "|   | ",
                    "|--/  "
            }, color);
            case 'E' -> new CustomChar(new String[]{
                    "|---- ",
                    "|____ ",
                    "|     ",
                    "|---- "
            }, color);
            case 'F' -> new CustomChar(new String[]{
                    "|---- ",
                    "|___  ",
                    "|     ",
                    "|     "
            }, color);
            case 'G' -> new CustomChar(new String[]{
                    "/----\\ ",
                    "| ___   ",
                    "|    \\ ",
                    "\\____/ "
            }, color);
            case 'H' -> new CustomChar(new String[]{
                    "|    | ",
                    "|____| ",
                    "|    | ",
                    "|    | "
            }, color);
            case 'I' -> new CustomChar(new String[]{
                    "---- ",
                    " |  ",
                    " |  ",
                    "---- "
            }, color);
            case 'J' -> new CustomChar(new String[]{
                    "  -| ",
                    "   | ",
                    "   | ",
                    "\\-| "
            }, color);
            case 'K' -> new CustomChar(new String[]{
                    "| / ",
                    "|/  ",
                    "|\\  ",
                    "| \\ "
            }, color);
            case 'L' -> new CustomChar(new String[]{
                    "|     ",
                    "|     ",
                    "|     ",
                    "|____ "
            }, color);
            case 'M' -> new CustomChar(new String[]{
                    "   /\\     /\\    ",
                    "  /  \\   /  \\   ",
                    " /    \\ /    \\  ",
                    "/               \\ "
            }, color);
            case 'N' -> new CustomChar(new String[]{
                    "|\\   | ",
                    "| \\  | ",
                    "|  \\ | ",
                    "|   \\| "
            }, color);
            case 'O' -> new CustomChar(new String[]{
                    "/----\\ ",
                    "|    | ",
                    "|    | ",
                    "\\----/ "
            }, color);
            case 'P' -> new CustomChar(new String[]{
                    "|---\\ ",
                    "|___/ ",
                    "|      ",
                    "|      "
            }, color);
            case 'Q' -> new CustomChar(new String[]{
                    "/----\\ ",
                    "|    | ",
                    "|    | ",
                    "\\----/\\ "
            }, color);
            case 'R' -> new CustomChar(new String[]{
                    "|---\\ ",
                    "|___/ ",
                    "| \\   ",
                    "|  \\  "
            }, color);
            case 'S' -> new CustomChar(new String[]{
                    "/---\\ ",
                    "\\___   ",
                    "     \\ ",
                    "\\---/ "
            }, color);
            case 'T' -> new CustomChar(new String[]{
                    "------ ",
                    "  |   ",
                    "  |   ",
                    "  |   "
            }, color);
            case 'U' -> new CustomChar(new String[]{
                    "|     | ",
                    "|     | ",
                    "|     | ",
                    "\\-----/ "
            }, color);
            case 'V' -> new CustomChar(new String[]{
                    "\\      / ",
                    " \\    /  ",
                    "  \\  /   ",
                    "   \\/    "
            }, color);
            case 'W' -> new CustomChar(new String[]{
                    "\\              / ",
                    " \\    /\\    /  ",
                    "  \\  /  \\  /   ",
                    "   \\/    \\/    "
            }, color);
            case 'X' -> new CustomChar(new String[]{
                    "\\  / ",
                    " \\/  ",
                    " /\\  ",
                    "/  \\ "
            }, color);
            case 'Y' -> new CustomChar(new String[]{
                    "\\  / ",
                    " \\/  ",
                    "  |   ",
                    "  |   "
            }, color);
            case 'Z' -> new CustomChar(new String[]{
                    "---/ ",
                    "  /  ",
                    " /   ",
                    "/___ "
            }, color);
            case ' ' -> new CustomChar(new String[]{
                    "       ",
                    "       ",
                    "       ",
                    "       "
            }, color);
            case '.' -> new CustomChar(new String[] {
                    "     ",
                    "     ",
                    "/\\ ",
                    "\\/ "
            }, color);
            case ':' -> new CustomChar(new String[]{
                    "/\\ ",
                    "\\/ ",
                    "/\\ ",
                    "\\/ "
            }, color);
            case '\n' -> new CustomChar(new String[]{
                    "",
                    "",
                    "",
                    ""
            }, color);
            case ',' -> new CustomChar(new String[]{
                    "  ",
                    "  ",
                    "|",
                    "/"
            }, color);
            case '!' -> new CustomChar(new String[]{
                    "/\\ ",
                    "\\/ ",
                    " |  ",
                    " |  "
            }, color);
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };

    }
}
