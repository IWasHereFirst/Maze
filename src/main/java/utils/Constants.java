package utils;

public class Constants {
    public static final int DEFAULT_NODE_DISTANCE = 1;
    public static final char SPACE = '.';
    public static final char WALL = '#';
    public static final char START = 'S';
    public static final char END = 'X';
    public static final char NEWLINE = '\n';
    public static final char TERMINATE_KEYBOARD_INPUT = '/';
    public static final String ALLOWED_CHARS = "\nEnd point -> " + Constants.END +
                                               "\nStart point -> " + Constants.START +
                                               "\nFree space -> " + Constants.SPACE +
                                               "\nWall space -> " + Constants.WALL +
                                               "\nTerminate keyboard input -> " + TERMINATE_KEYBOARD_INPUT;
}
