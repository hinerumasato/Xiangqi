package models;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Piece {

    public Chariot(Color color) {
        super(color);
        this.code = 3;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "X";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Chariot newInstance = new Chariot(this.color);
        Point p = this.getPoint();
        newInstance.setPoint(new Point(p.getX(), p.getY()));
        return newInstance;
    }


    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        Board board = Board.getInstance();
        // Check y for right
        for (int i = y + 1; i < Board.BOARD_COLS; i++) {
            Point p = new Point(x, i);
            if (board.isEmptyPosition(p))
                result.add(p);
            else if (board.isOpponentPiece(this, p)) {
                result.add(p);
                break;
            } else
                break;
        }
        // check y for left
        for (int i = y - 1; i >= 0; i--) {
            Point p = new Point(x, i);
            if (board.isEmptyPosition(p))
                result.add(p);
            else if (board.isOpponentPiece(this, p)) {
                result.add(p);
                break;
            } else
                break;
        }
        // And also check x for 2 direction top and bottom
        for (int i = x + 1; i < Board.BOARD_ROWS; i++) {
            Point p = new Point(i, y);
            if (board.isEmptyPosition(p))
                result.add(p);
            else if (board.isOpponentPiece(this, p)) {
                result.add(p);
                break;
            } else
                break;
        }
        
        for (int i = x - 1; i >= 0; i--) {
            Point p = new Point(i, y);
            if (board.isEmptyPosition(p))
                result.add(p);
            else if (board.isOpponentPiece(this, p)) {
                result.add(p);
                break;
            } else
                break;
        }

        return result;
        
    }

}
