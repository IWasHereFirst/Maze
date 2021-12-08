package processClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractFindPathInputReaderTest {

    @Test
    void allowedCharactersCheck() {
        boolean test1 = AbstractFindPathInputReader.allowedCharactersCheck('a');
        boolean test2 = AbstractFindPathInputReader.allowedCharactersCheck('f');
        assertTrue(test1);
        assertTrue(test2);
    }

    @Test
    void processInput() {
        char[] input = {'S', '.', '.','\n', '.', '#', 'f','\n', '.', '.', 'X'};
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AbstractFindPathInputReader.processInput(input);
        }, "IllegalArgumentException was expected");
        Assertions.assertEquals("Inconsistent line length", thrown.getMessage());
    }
}