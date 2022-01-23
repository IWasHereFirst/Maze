import processClasses.*;

/**
 * To find and display path use following
 * File:            PathFinder.displayPath(processClasses.FindPathInputReaderFile.read("[file_name].txt"));
 * Standard Input:  PathFinder.displayPath(processClasses.FindPathInputReaderStdIn.read());
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // Example of finding the shortest path from a file
        PathFinder.displayPath(FindPathInputReaderFile.read("map.txt"));
        // some new changes

    }
}
