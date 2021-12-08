package utils;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    private String name;
    private char type;
    private boolean visited = false;
    private int global = Integer.MAX_VALUE;
    private int local = Integer.MAX_VALUE;
    private int x;
    private int y;
    private List<Node> neighbours;
    private String parent;

    public Node(String name, char type) {
        this.name = name;
        this.type = type;
        String[] coordinates = name.split("_");
        this.x = Integer.parseInt(coordinates[0]);
        this.y = Integer.parseInt(coordinates[1]);
        neighbours = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getGlobal() {
        return global;
    }

    public int getLocal() {
        return local;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getParent() {
        return parent;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    // Setters
    public void addNeighbour(Node node){
        neighbours.add(node);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node o) {
        return String.valueOf(this.getGlobal()).compareTo(String.valueOf(o.getGlobal()));
    }

    @Override
    public String toString() {
        return "[" + this.name + "]";
    }
}

