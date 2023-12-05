package AI;

import java.util.ArrayList;
import java.util.List;

import models.Board;

public class Node {
    private Board board;
    private List<Node> neighbors;
    private int heuristicValue;

    public Node(Board board) {
        this.board = board;
        this.neighbors = new ArrayList<Node>();
    }

    public int heuristic() {
        return Heuristic.compute(board);
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public void addNeighbors(List<Node> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    public void addNeighbors(Node... neighbors) {
        List<Node> neighborNodes = new ArrayList<Node>();
        for (Node node : neighbors) {
            neighborNodes.add(node);
        }
        addNeighbors(neighborNodes);
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

}
