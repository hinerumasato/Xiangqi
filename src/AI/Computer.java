package AI;

import models.Board;

public class Computer {
    private Board board;
    private IMoveAlgorithm moveAlgorithm;

    public Computer(Board board) {
        this.board = board;
    }

    public Computer(Board board, IMoveAlgorithm moveAlgorithm) {
        this.board = board;
        this.moveAlgorithm = moveAlgorithm;
    } 

    public void move() {
        int bestMove = moveAlgorithm.move();
        Node node = ((AMoveAlgorithm) moveAlgorithm).getNode();
        System.out.println(node.getNeighbors().size());

        for (Node neighbor : node.getNeighbors()) {
            if (neighbor.getHeuristicValue() == bestMove) {
                Board nextBoard = neighbor.getBoard();
                Board.setInstance(nextBoard);
                return;
            }

        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public IMoveAlgorithm getMoveAlgorithm() {
        return moveAlgorithm;
    }

    public void setMoveAlgorithm(IMoveAlgorithm moveAlgorithm) {
        this.moveAlgorithm = moveAlgorithm;
    }
}
