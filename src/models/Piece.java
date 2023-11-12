package models;

import java.util.List;

public abstract class Piece {
    protected Color color;
    protected Point point;
    protected int code;
    protected String strCode;

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
        List<Point> possibleMoves = getAllPossibleMoves();
        return possibleMoves.contains(point);
    }
    public abstract List<Point> getAllPossibleMoves();
    public void move(Point point) {
        if(canMove(point))
            this.setPoint(point);
        Board.getInstance().initBoard();
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

    public boolean isOpponentPiece(Point point) {
        Piece opponentPiece = Board.getInstance().getPieceByPoint(point);
        if(opponentPiece == null)
            return false;
        return !this.getColor().equals(opponentPiece.getColor());
    }

}
