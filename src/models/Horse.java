package models;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;

public class Horse extends Piece {

    public Horse(Color color) {
        super(color);
        this.code = 6;
        if (color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = Constants.HORSE_STR_CODE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Horse newInstance = new Horse(this.color);
        Point p = this.getPoint();
        newInstance.setPoint(new Point(p.getX(), p.getY()));
        return newInstance;
    }

    private boolean isValidTop(Point p) {
        Board board = Board.getInstance();
        return p.getX() >= 0 && board.isEmptyPosition(p);
    }

    private boolean isValidBottom(Point p) {
        Board board = Board.getInstance();
        return p.getX() < Board.BOARD_ROWS && board.isEmptyPosition(p);
    }

    private boolean isValidLeft(Point p) {
        Board board = Board.getInstance();
        return p.getY() >= 0 && board.isEmptyPosition(p);
    }

    private boolean isValidRight(Point p) {
        Board board = Board.getInstance();
        return p.getY() < Board.BOARD_COLS && board.isEmptyPosition(p);
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        Board board = Board.getInstance();
        Point horseTop = new Point(getPoint().getX() - 1, getPoint().getY());
        Point horseBottom = new Point(getPoint().getX() + 1, getPoint().getY());
        Point horseLeft = new Point(getPoint().getX(), getPoint().getY() - 1);
        Point horseRight = new Point(getPoint().getX(), getPoint().getY() + 1);

        if (isValidTop(horseTop)) {
            int x = horseTop.getX();
            int y = horseTop.getY();
            if (x - 1 >= 0) {
                if (y + 1 < Board.BOARD_COLS) {
                    Point p = new Point(x - 1, y + 1);
                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }

                if (y - 1 >= 0) {
                    Point p = new Point(x - 1, y - 1);

                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }
            }
        }

        if (isValidBottom(horseBottom)) {
            int x = horseBottom.getX();
            int y = horseBottom.getY();
            if (x + 1 < Board.BOARD_ROWS) {
                if (y + 1 < Board.BOARD_COLS) {
                    Point p = new Point(x + 1, y + 1);
                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }

                if (y - 1 >= 0) {
                    Point p = new Point(x + 1, y - 1);

                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }
            }
        }

        // Is valid left
        if (isValidLeft(horseLeft)) {
            int x = horseLeft.getX();
            int y = horseLeft.getY();
            if (y - 1 >= 0) {
                if (x - 1 >= 0) {
                    Point p = new Point(x - 1, y - 1);
                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }

                if (x + 1 < Board.BOARD_ROWS) {
                    Point p = new Point(x + 1, y - 1);
                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }
            }
        }

        // Is valid right
        if (isValidRight(horseRight)) {
            int x = horseRight.getX();
            int y = horseRight.getY();
            if (y + 1 < Board.BOARD_COLS) {
                if (x - 1 >= 0) {
                    Point p = new Point(x - 1, y + 1);
                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }
                if (x + 1 < Board.BOARD_ROWS) {
                    Point p = new Point(x + 1, y + 1);
                    if (board.isEmptyPosition(p) || board.isOpponentPiece(this, p))
                        result.add(p);
                }
            }
        }

        return result;
    }

}
