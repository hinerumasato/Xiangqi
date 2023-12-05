package models;

import java.util.List;
import java.util.stream.Collectors;

import enums.EColor;

public abstract class Piece implements Cloneable {
    protected EColor color;
    protected Point point;
    protected int code;
    protected String strCode;
    protected int value;

    @Override
    protected abstract Object clone() throws CloneNotSupportedException;

    public boolean isOverLake(Point point) {
        int x = point.getX();
        if (getColor().equals(EColor.RED)) {
            return x >= 0 && x <= 4;
        } else
            return x >= 5 && x <= 9;
    }

    public boolean isInArch() {
        int x = point.getX();
        int y = point.getY();
        if (getColor().equals(EColor.BLACK)) {
            return y >= 3 && y <= 5 && x >= 0 && x <= 2;
        } else {
            return y >= 3 && y <= 5 && x >= 7 && x <= 9;
        }
    }

    public Piece(EColor color) {
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

    public EColor getColor() {
        return color;
    }

    public void setColor(EColor color) {
        this.color = color;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public boolean canMove(Board board, Point point) {
        List<Point> possibleMoves = filterPossibleMoves(board);
        return possibleMoves.contains(point);
    }

    private void resetPiece(Piece piece, Point point) {
        Board board = Board.getInstance();
        if (piece != null) {
            piece.setPoint(point);
            board.initBoard();
        }
    }

    public boolean isCheckmateAfterMove(Point point) {
        Board board = Board.getInstance();
        Point originPoint = getPoint();
        Piece opponentPiece = board.getPieceByPoint(point);
        if (opponentPiece != null)
            opponentPiece.setPoint(new Point(-1, -1));
        tryMove(point);
        if (board.isCheckmate(getColor())) {
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

    public abstract List<Point> getAllPossibleMoves(Board board);

    public List<Point> filterPossibleMoves(Board board) {
        List<Point> possibleMoves = getAllPossibleMoves(board);
        List<Point> result = possibleMoves.stream()
                .filter(point -> !isCheckmateAfterMove(point))
                .collect(Collectors.toList());
        return result;
    }

    public boolean move(Board board, Point point) {
        boolean canMove;
        if (canMove = canMove(board, point)) {
            Piece opponentPiece = board.getPieceByPoint(point);
            if (opponentPiece != null) {
                board.removePiece(opponentPiece);
            }
            this.setPoint(point);
        }
        board.initBoard();
        return canMove;
    }

    public void forceMove(Board board, Point point) {
        Piece opponentPiece = board.getPieceByPoint(point);
        if (opponentPiece != null) {
            board.removePiece(opponentPiece);
        }
        this.setPoint(point);
        board.initBoard();
    }

    public boolean isRedPiece(Point point) {
        Piece piece = Board.getInstance().getPieceByPoint(point);
        if (piece == null)
            return false;
        return piece.getColor().equals(EColor.RED);
    }

    public boolean isBlackPiece(Point point) {
        Piece piece = Board.getInstance().getPieceByPoint(point);
        if (piece == null)
            return false;
        return piece.getColor().equals(EColor.BLACK);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
