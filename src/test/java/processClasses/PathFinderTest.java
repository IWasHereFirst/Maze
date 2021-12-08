package processClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathFinderTest {

    @Test
    void displayPath() throws Exception {
        String output = PathFinder.displayPath(FindPathInputReaderFile.read("map.txt"));
        String expected = "d,d,d,d,d,d,d,d,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,r,d,d,r,r,r,r";
        assertEquals(expected, output);
    }
}