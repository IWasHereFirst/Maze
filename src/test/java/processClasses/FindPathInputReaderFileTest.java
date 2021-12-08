package processClasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FindPathInputReaderFileTest {

    @Test
    void read() throws IllegalArgumentException, IOException {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FindPathInputReaderFile.read("missingLineMap.txt");
        }, "IllegalArgumentException was expected");

        Assertions.assertEquals("Inconsistent line length", thrown.getMessage());
    }

}