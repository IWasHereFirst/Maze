package processClasses;

import utils.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindPathInputReaderFile extends AbstractFindPathInputReader {

    public static List<Node> read(String fileName) throws IOException {

        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            List<Node> nodes = new ArrayList<>();
            char lastChar = '\0';
            char singleChar;
            int firstLineLength = 0;
            boolean firstLineCounted = false;
            int otherLineLength = 0;
            boolean otherLineCounted = false;
            int coordinateX = 1;
            int coordinateY = 1;
            int singleCharInt;
            boolean foundStart = false;
            boolean foundEnd = false;

            while((singleCharInt = bufferedInputStream.read()) != -1) {
                singleChar = (char) singleCharInt;
                if(allowedCharactersCheck(singleChar)) continue;
                if(singleChar == Constants.START) foundStart = true;                                 // Start point is required
                if(singleChar == Constants.END) foundEnd = true;                                     // End point is required
                if(!(singleChar == Constants.NEWLINE && singleChar == lastChar)) {                   // Ignore double \n input

                    if(firstLineCounted && singleChar == Constants.NEWLINE) otherLineCounted = true; // Counting other lines length disabled
                    if(firstLineCounted && !otherLineCounted) otherLineLength++;                    // Done counting first line, count other lines
                    if(singleChar == Constants.NEWLINE) firstLineCounted = true;                     // Counting first line length disabled
                    if(!firstLineCounted) firstLineLength++;                                        // Counting first line length
                    if(firstLineCounted && otherLineCounted){                                       // Lines counted, do they match?
                        if(firstLineLength == otherLineLength){                                     // Yes, reset stats for other lines to count against the first line
                            otherLineLength = 0;
                            otherLineCounted = false;
                        } else {                                                                    // Lines didn't match, throw exception
                            throw new IllegalArgumentException("Inconsistent line length");
                        }
                    }
                    // Things seems alright, fill the list with nodes
                    if(singleChar != Constants.NEWLINE) {
                        Node myNode = new Node(coordinateX + "_" + coordinateY, singleChar);
                        nodes.add(myNode);
                        if(myNode.getType() == 'X') PathFinder.setEndPointCallback(myNode);
                        if(myNode.getType() == 'S') PathFinder.setStartPointCallback(myNode);
                    }
                    coordinateX++;
                    if(singleChar == Constants.NEWLINE){
                        coordinateY++;
                        coordinateX = 1;
                    }
                }
                lastChar = singleChar;
            }
            // Last line length check
            if(firstLineLength != otherLineLength){
                throw new IllegalArgumentException("Inconsistent line length");
            } else if(!foundStart || !foundEnd){
                throw new IllegalArgumentException("Start point " + Constants.START + " and end point " + Constants.END + " are required");
            }
            return addNeighboursToNodes(nodes);
        }
    }
}

