package models;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece implements Cloneable {
    protected Color color;
    protected Point point;
    protected int code;
    protected String strCode;

    @Override
    protected abstract Object clone() throws CloneNotSupportedException;

    public boolean isOverLake(Point point) {
        int x = point.getX();
        if (getColor().equals(Color.RED)) {
            return x >= 0 && x <= 4;
        } else
            return x >= 5 && x <= 9;
    }

    public boolean isInArch() {
        int x = point.getX();
        int y = point.getY();
        if(getColor().equals(Color.BLACK)) {
            return y >= 3 && y <= 5 && x >= 0 && x <= 2;
        } else {
            return y >= 3 && y <= 5 && x >= 7 && x <= 9;
        }
    }

    public Piece(Color color) {
        this.color = color;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public boolean canMove(Point point) {
        List<Point> possibleMoves = filterPossibleMoves();
        return possibleMoves.contains(point);
    }

    private void resetPiece(Piece piece, Point point) {
        Board board = Board.getInstance();
        if(piece != null) {
            piece.setPoint(point);
            board.initBoard();
        }
    }

    public boolean isCheckmateAfterMove(Point point) {
        Board board = Board.getInstance();
        Point originPoint = getPoint();
        Piece opponentPiece = board.getPieceByPoint(point);
        if(opponentPiece != null)
            opponentPiece.setPoint(originPoint);
        tryMove(point);
        if(board.isCheckmate(getColor())) {
            tryMove(originPoint);
            resetPiece(opponentPiece, point);
            return true;
        } else {
            tryMove(originPoint);
            resetPiece(opponentPiece, point);
            return false;
        }
    }

    public void tryMove(Point point) {
        Board board = Board.getInstance();
        setPoint(point);
        
        board.initBoard();
    }

    public abstract List<Point> getAllPossibleMoves();
    public List<Point> filterPossibleMoves() {
        List<Point> possibleMoves = getAllPossibleMoves();
        List<Point> result = possibleMoves.stream()
            .filter(point -> !isCheckmateAfterMove(point))
            .collect(Collectors.toList());
        return result;
    }

    public boolean move(Point point) {
        boolean canMove;
        Board board = Board.getInstance();
        if(canMove = canMove(point)) {
            Piece opponentPiece = board.getPieceByPoint(point);
            if(opponentPiece != null) {
                board.removePiece(opponentPiece);
            }
            this.setPoint(point);
        }
        board.initBoard();
        return canMove;
    }

    public boolean isRedPiece(Point point) {
        Piece piece = Board.getInstance().getPieceByPoint(point);
        if(piece == null)
            return false;
        return piece.getColor().equals(Color.RED);
    }

    public boolean isBlackPiece(Point point) {
        Piece piece = Board.getInstance().getPieceByPoint(point);
        if(piece == null)
            return false;
        return piece.getColor().equals(Color.BLACK);
    }

}
