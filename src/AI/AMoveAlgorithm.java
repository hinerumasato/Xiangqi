package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.EColor;
import models.Board;
import models.Piece;
import models.Point;

public abstract class AMoveAlgorithm implements IMoveAlgorithm {

    protected Node node;
    protected static final int DEPTH = 3;
    protected static final boolean MAX = true;
    protected static final boolean MIN = false;

    public AMoveAlgorithm() {
        this.node = new Node(Board.getInstance());
        getBoardTree(node, DEPTH, MIN);
    }

    public AMoveAlgorithm(Node node) {
        this.node = node;
        getBoardTree(node, DEPTH, MIN);
    }

    private List<Board> getFutureBoards(Board board, EColor color) {
        List<Board> boards = new ArrayList<Board>();
        List<Piece> pieces = board.getPieces();
        pieces.forEach(piece -> {
            if (piece.getColor().equals(color)) {
                List<Point> points = piece.filterPossibleMoves(board);
                points.forEach(point -> {
                    Point oldPoint = piece.getPoint();
                    Piece opponentPiece = null;
                    Point opponentPiecePoint = null;
                    if (board.isOpponentPiece(piece, point)) {
                        opponentPiece = board.getPieceByPoint(point);
                        opponentPiecePoint = opponentPiece.getPoint();
                        opponentPiece.setPoint(new Point(-1, -1));
                    }
                    piece.tryMove(point);
                    Board newBoard = new Board(board.copyPieces());
                    boards.add(newBoard);
                    piece.setPoint(oldPoint);

                    if (opponentPiece != null && opponentPiecePoint != null)
                        opponentPiece.setPoint(opponentPiecePoint);
                });
            }
        });

        return boards;
    }

    private void getBoardTree(Node node, int depth, boolean isMax) {
        if (depth == 0)
            return;

        EColor color = isMax ? EColor.RED : EColor.BLACK;
        List<Board> boards = getFutureBoards(node.getBoard(), color);
        List<Node> neighbors = boards.stream().map(b -> new Node(b)).collect(Collectors.toList());

        node.addNeighbors(neighbors);

        for (Node neighbor : node.getNeighbors()) {
            getBoardTree(neighbor, depth - 1, !isMax);
        }
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
