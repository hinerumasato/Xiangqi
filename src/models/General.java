package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Constants.Constants;
import enums.EColor;

public class General extends Piece {

    public General(EColor color) {
        super(color);
        this.code = 5;
        this.value = Constants.GENERAL_VALUE;
        if(color.equals(EColor.BLACK)) {
            this.code *= -1;
            this.value *= -1;
        }
        this.strCode = Constants.GENERAL_STR_CODE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        General newInstance = new General(this.color);
        Point p = this.getPoint();
        newInstance.setPoint(new Point(p.getX(), p.getY()));
        return newInstance;
    }

   private List<Point> isValidPoints(Board board, List<Point> points) {
        List<Point> result = new ArrayList<Point>();
        for (Point point : points) {
            if(board.isInArch(getColor(), point) && (board.isEmptyPosition(point) || board.isOpponentPiece(this, point)))
                result.add(point);
        }
        return result;
    }


    @Override
    public List<Point> getAllPossibleMoves(Board board) {
        int x = point.getX();
        int y = point.getY();

        Point top = new Point(x + 1, y);
        Point bottom = new Point(x -1 , y);
        Point left = new Point(x, y - 1);
        Point right = new Point(x, y + 1);

        return isValidPoints(board, new ArrayList<Point>(Arrays.asList(top, bottom, left, right)));

    }
    
}
