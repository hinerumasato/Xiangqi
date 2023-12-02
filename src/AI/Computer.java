package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enums.EColor;
import models.Board;
import models.Piece;
import models.Point;

public class Computer {
    private Board board;

    public Computer() {
        this.board = Board.getInstance();
    }

    public void move() {
        Random rd = new Random();
        List<Board> moves = getFutureBoards();
        if(moves.size() > 0)
            Board.setInstance(moves.get(rd.nextInt(moves.size())));
    }

    public List<Board> getFutureBoards() {
        List<Board> boards = new ArrayList<Board>();
        List<Piece> pieces = board.getPieces();
        pieces.forEach(piece -> {
            if(piece.getColor().equals(EColor.BLACK)) {
                List<Point> points = piece.filterPossibleMoves();
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
                    boards.add(new Board(board.copyPieces()));
                    piece.setPoint(oldPoint);

                    if(opponentPiece != null && opponentPiecePoint != null)
                        opponentPiece.setPoint(opponentPiecePoint); 
                });
            }
                
        });

        return boards;
    }
}
