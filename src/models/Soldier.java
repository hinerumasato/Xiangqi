package models;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Piece {

    public Soldier(Color color) {
        super(color);
        this.code = 7;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "B";
    }

   private boolean isOverLake(Point point) {
        int x = point.getX();
        if (getColor().equals(Color.RED)) {
            return x > 0 && x <= 4;
        } else
            return x >= 5 && x <= 9;
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        Board board = Board.getInstance();
        
        Point now = new Point(x, y);
        Point top = new Point(x - 1, y);
        Point bottom = new Point(x + 1, y);
        Point left = new Point(x, y - 1);
        Point right = new Point(x, y + 1);

        if (isOverLake(point)) {
            // check for top
            if (getColor().equals(Color.RED)) {
                if (board.isEmptyPosition(top)) {
                    result.add(top);
                } else if (isOpponentPiece(top)) {
                    result.add(top);
                } else
                    result.add(now);
            } // check for bottom
            else {
                if (board.isEmptyPosition(bottom)) {
                    result.add(bottom);
                } else if (isOpponentPiece(bottom)) {
                    result.add(bottom);
                } else {
                    result.add(now);
                }
            }

            // check for right
            if (y < 10) {
                if (board.isEmptyPosition(right)) {
                    result.add(right);
                } else if (isOpponentPiece(right)) {
                    result.add(right);
                } else {
                    result.add(now);
                }
            } else {
                // result.add(now);
            }

            // check for left
            if (y > 0) {
                if (board.isEmptyPosition(left)) {
                    result.add(left);
                } else if (isOpponentPiece(left)) {
                    result.add(left);
                } else {
                    result.add(now);
                }
            } else {
                // result.add(now);
            }
        } else {
            if (getColor().equals(Color.RED)) {
                if (board.isEmptyPosition(top)) {
                    result.add(top);
                } else {
                    result.add(now);
                }
            } else {
                if (board.isEmptyPosition(bottom)) {
                    result.add(bottom);
                } else {
                    result.add(now);
                }
            }
        }
        return result;
    }
    
}
