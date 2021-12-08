package processClasses;

import utils.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for finding the shortest way from both file and standard input
 */
public class PathFinder {

    static Node endPoint;
    static Node startPoint;

    public static String displayPath(List<Node> list) throws Exception {
        int endPointX = 0;
        int endPointY = 0;

        List<Node> nodesToTest = new ArrayList<>();
        if(endPoint != null) {
            endPointX = endPoint.getX();
            endPointY = endPoint.getY();
        }
        if(startPoint != null) {
            startPoint.setLocal(0);
            startPoint.setGlobal(Math.abs(startPoint.getX() - endPointX) + Math.abs(startPoint.getY() - endPointY));
        }
        // 1. Add start point to the list to test
        nodesToTest.add(startPoint);
        // 2. Test all neighbours
        for(int i = 0; i < nodesToTest.size(); i++) {
            int addedNodes = 0;
            Node testNode = nodesToTest.get(i);
            if(testNode == endPoint || testNode.getType() == '#') {
                testNode.setVisited(true);
                nodesToTest.remove(testNode);
                i--;
                continue;
            }
            for(int j = 0; j < testNode.getNeighbours().size(); j++) {
                Node neighbour = testNode.getNeighbours().get(j);
                int currentX = neighbour.getX();
                int currentY = neighbour.getY();
                if(!neighbour.isVisited()) {
                    nodesToTest.add(neighbour); // If neighbour wasn't visited, add neighbour to the list
                    addedNodes++;
                    Collections.sort(nodesToTest);  // Sort list
                }
                // If currentNode local + distance < neighbourNode local, set parent and update local
                if(testNode.getLocal() + Constants.DEFAULT_NODE_DISTANCE < neighbour.getLocal()) {
                    neighbour.setParent(testNode.getName());                                    // Set parent Name
                    neighbour.setLocal(testNode.getLocal() + Constants.DEFAULT_NODE_DISTANCE);  // Update local variable
                    neighbour.setGlobal(neighbour.getLocal() + (Math.abs(currentX - endPointX) + Math.abs(currentY - endPointY)));    // Update global variable
                }
            }
            testNode.setVisited(true);
            nodesToTest.remove(testNode);
            if(addedNodes > 0) i = -1;
        }

        startPoint.setParent(startPoint.getName());

        // When the nodesToTest is empty find through parents the shortest way and invert it
        StringBuilder pathWay = new StringBuilder();
        pathWay.append(findPath(endPoint));
        Node lastPoint = endPoint;
        while(lastPoint != startPoint) {
            for(int i = 0; i < list.size(); i++) {
                Node nodePath = list.get(i);
                if(lastPoint.getParent().equals(nodePath.getName())) {
                    pathWay.append(",").append(findPath(nodePath));
                    lastPoint = nodePath;
                    list.remove(nodePath);
                }
            }
        }
        String output = pathWay.reverse().substring(5, pathWay.length());
        System.out.println(output); // Clear the input and print the result
        return output;
    }
    private static String findPath(Node n) throws Exception {
        String[] startSplit = n.getName().split("_");
        int startX = Integer.parseInt(startSplit[0]);
        int startY = Integer.parseInt(startSplit[1]);
        if(n.getParent() == null) throw new Exception("Endpoint is not accessible.");
        String[] endSplit = n.getParent().split("_");
        int endX = Integer.parseInt(endSplit[0]);
        int endY = Integer.parseInt(endSplit[1]);
        // UP
        if(startX == endX && startY > endY) return "d";
        // RIGHT
        if(startX < endX && startY == endY) return "l";
        // DOWN
        if(startX == endX && startY < endY) return "u";
        // LEFT
        if(startX > endX && startY == endY) return "r";
        return null;
    }
    public static void setStartPointCallback(Node node){
        startPoint = node;
    }
    public static void setEndPointCallback(Node node){
        endPoint = node;
    }
}
