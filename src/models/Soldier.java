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
        Board board = Board.getInstance();

        int x = getPoint().getX();
        int y = getPoint().getY();
        
        Point top;

        if(this.getColor().equals(Color.RED)) {
            top = new Point(x - 1, y);
        } else top = new Point(x + 1, y);

        if(board.isEmptyPosition(top) || isOpponentPiece(top)) {
            result.add(top);
        }

        if(isOverLake(point)) {

            if(y - 1 >= 0) {
                Point pLeft = new Point(x, y - 1);
                if(board.isEmptyPosition(pLeft) || isOpponentPiece(pLeft))
                    result.add(pLeft);
            }

            if(y + 1 < Board.BOARD_COLS) {
                Point pRight = new Point(x, y + 1);
                if(board.isEmptyPosition(pRight) || isOpponentPiece(pRight))
                    result.add(pRight);
            }
        }

        return result;
    }
    
}
