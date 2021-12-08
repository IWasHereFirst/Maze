package processClasses;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Reading superclass
 */
public class AbstractFindPathInputReader {

    // Method checking for allowed characters in input
    static boolean allowedCharactersCheck(char input){
        return input != Constants.WALL
                && input != Constants.END
                && input != Constants.START
                && input != Constants.SPACE
                && input != Constants.NEWLINE;
    }
    // Method processing input
    static List<Node> processInput(char[] fileBytes){

        List<Node> nodes = new ArrayList<>();
        char lastChar = '\0';
        char singleChar;
        int firstLineLength = 0;
        boolean firstLineCounted = false;
        int otherLineLength = 0;
        boolean otherLineCounted = false;
        int coordinateX = 1;
        int coordinateY = 1;
        boolean foundStart = false;
        boolean foundEnd = false;

        for(char b : fileBytes) {
            singleChar = b;
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
            lastChar = singleChar;                                                              // Remember last char. to check for double \n input
        }
        // Last line length check
        if(firstLineLength != otherLineLength){
            throw new IllegalArgumentException("Inconsistent line length");
        } else if(!foundStart || !foundEnd){
            throw new IllegalArgumentException("Start point " + Constants.START + " and end point " + Constants.END + " are required");
        }
        return addNeighboursToNodes(nodes);
    }
    // Finishing method for the map (adding neighbours to the list of nodes)
    static List<Node> addNeighboursToNodes(List<Node> myList){
        int sizeX = myList.get(myList.size()-1).getX(); // 4
        int sizeY = myList.get(myList.size()-1).getY(); // 4
        // List all nodes
        for(Node node: myList){
            int nodePositionX = node.getX(); // 1
            int nodePositionY = node.getY(); // 1
            int index = myList.indexOf(node);
            // And add possible neighbours to the list
            // Add right neighbour
            if(nodePositionX < sizeX) node.addNeighbour(myList.get(index + 1));
            // Add left neighbour
            if(nodePositionX > 1) node.addNeighbour(myList.get(index - 1));
            // Add top neighbour
            if(nodePositionY > 1) node.addNeighbour(myList.get(index - sizeX));
            // Add bottom neighbour
            if(nodePositionY < sizeY) node.addNeighbour(myList.get(index + sizeX));
        }
        return myList;
    }

}
