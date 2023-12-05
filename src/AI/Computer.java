package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.EColor;
import models.Board;
import models.Piece;
import models.Point;

public class Computer {
    private Board board;
    private static final int DEPTH = 3;
    private static final boolean MAX = true;
    private static final boolean MIN = false;

    public Computer(Board board) {
        this.board = board;
    }

    public void move() {
        Node node = new Node(board);
        getBoardTree(node, DEPTH, MIN);

        // Node temp = node.getNeighbors().get(25).getNeighbors().get(0).getNeighbors().get(27);
        // temp.getBoard().printStrCodeBoard();
        int bestMove = minimax(node, DEPTH, MIN);
        System.out.println(bestMove);

        for (Node neighbor : node.getNeighbors()) {
            if(neighbor.getHeuristicValue() == bestMove) {
                Board nextBoard = neighbor.getBoard();
                // System.out.println(Heuristic.compute(neighbor.getNeighbors().get(0).getBoard()));
                // neighbor.getNeighbors().get(0).getBoard().printStrCodeBoard();
                // Piece test = neighbor.getBoard().getPieceByPoint(new Point(9, 0));
                // if(test != null)
                //     System.out.println(test.filterPossibleMoves(nextBoard));
                Board.setInstance(nextBoard);
                return;
            }

        }
    }

    public void getBoardTree(Node node, int depth, boolean isMax) {
        if(depth == 0)
            return;
        
        EColor color = isMax ? EColor.RED : EColor.BLACK;
        List<Board> boards = getFutureBoards(node.getBoard(), color);
        List<Node> neighbors = boards.stream().map(b -> new Node(b)).collect(Collectors.toList());
        

        node.addNeighbors(neighbors);

        for (Node neighbor : node.getNeighbors()) {
            getBoardTree(neighbor, depth - 1, !isMax);
        }
    }

    public int minimax(Node node, int depth, boolean isMax) {
        if(depth == 0) {
            return Heuristic.compute(node.getBoard());
        }
        else {
            if(isMax) {
                int bestValue = Integer.MIN_VALUE;
                for (Node neighbor : node.getNeighbors()) {
                    int heuristic = Math.max(bestValue, minimax(neighbor, depth - 1, MIN));
                    neighbor.setHeuristicValue(heuristic);
                    if(heuristic > bestValue)
                        bestValue = heuristic;
                }
                return bestValue;
            } else {
                int wrostValue = Integer.MAX_VALUE;
                for (Node neighbor : node.getNeighbors()) {
                    int heuristic = Math.min(wrostValue, minimax(neighbor, depth - 1, MAX));
                    neighbor.setHeuristicValue(heuristic);
                    if(heuristic < wrostValue) 
                        wrostValue = heuristic;
                }

                return wrostValue;
            }
        }
    }

    public List<Board> getFutureBoards(Board board, EColor color) {
        List<Board> boards = new ArrayList<Board>();
        List<Piece> pieces = board.getPieces();
        pieces.forEach(piece -> {
            if(piece.getColor().equals(color)) {
                List<Point> points = piece.filterPossibleMoves(board);
                points.forEach(point -> {
                    Point oldPoint = piece.getPoint();
                    Piece opponentPiece = null;
                    Point opponentPiecePoint = null;
                    if(board.isOpponentPiece(piece, point)) {
                        opponentPiece = board.getPieceByPoint(point);
                        opponentPiecePoint = opponentPiece.getPoint();
                        opponentPiece.setPoint(new Point(-1, -1));
                    }
                    piece.tryMove(point);
                    Board newBoard = new Board(board.copyPieces());
                    boards.add(newBoard);
                    piece.setPoint(oldPoint);

                    if(opponentPiece != null && opponentPiecePoint != null)
                        opponentPiece.setPoint(opponentPiecePoint); 
                });
            }
                
        });

        return boards;
    }
}
