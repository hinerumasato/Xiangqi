package AI;

import java.util.ArrayList;
import java.util.List;

import enums.EColor;
import models.Board;
import models.Piece;
import models.Point;

public abstract class AMoveAlgorithm implements IMoveAlgorithm {

    protected Node node;
    protected static final int DEPTH = 5;
    protected static final boolean MAX = true;
    protected static final boolean MIN = false;

    public AMoveAlgorithm() {
        this.node = new Node(Board.getInstance());
    }

    public AMoveAlgorithm(Node node) {
        this.node = node;
    }

    public List<Node> getFutureNodes(Board board, EColor color) {
        List<Node> result = new ArrayList<Node>();
        List<Board> futureBoards = getFutureBoards(board, color);
        for (Board fBoard : futureBoards) {
            result.add(new Node(fBoard));
        }
        return result;
    }

    public List<Board> getFutureBoards(Board board, EColor color) {
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

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
