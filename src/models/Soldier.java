package models;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import enums.EColor;

public class Soldier extends Piece {

    public Soldier(EColor color) {
        super(color);
        this.code = 7;
        this.value = Constants.SOLDIER_VALUE;
        if(color.equals(EColor.BLACK)) {
            this.code *= -1;
            this.value *= -1;
        }
        this.strCode = Constants.SOLDIER_STR_CODE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Soldier newInstance = new Soldier(this.color);
        Point p = this.getPoint();
        newInstance.setPoint(new Point(p.getX(), p.getY()));
        return newInstance;
    }

    @Override
    public List<Point> getAllPossibleMoves(Board board) {
        List<Point> result = new ArrayList<Point>();

        int x = getPoint().getX();
        int y = getPoint().getY();
        
        Point top;

        if(this.getColor().equals(EColor.RED)) {
            top = new Point(x - 1, y);
        } else top = new Point(x + 1, y);

        if(board.isEmptyPosition(top) || board.isOpponentPiece(this, top)) {
            result.add(top);
        }

        if(isOverLake(point)) {

            if(y - 1 >= 0) {
                Point pLeft = new Point(x, y - 1);
                if(board.isEmptyPosition(pLeft) || board.isOpponentPiece(this, pLeft))
                    result.add(pLeft);
            }

            if(y + 1 < Board.BOARD_COLS) {
                Point pRight = new Point(x, y + 1);
                if(board.isEmptyPosition(pRight) || board.isOpponentPiece(this, pRight))
                    result.add(pRight);
            }
        }

        return result;
    }
    
}
