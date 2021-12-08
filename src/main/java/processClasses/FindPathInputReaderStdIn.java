package processClasses;

import utils.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class reading Standard keyboard input
 */
public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {

    public static List<Node> read() throws IOException {

        System.out.println("Use following characters for input:\n" + Constants.ALLOWED_CHARS);
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        String stringInput;

        while(!Objects.equals(stringInput = scanner.nextLine(), "/")) {
            builder.append(stringInput).append("\n");
        }
        scanner.close();
        char[] input = builder.substring(0, builder.length()-1).toCharArray();
        return processInput(input);
    }
}
