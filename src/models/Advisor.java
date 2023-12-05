package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.Constants;
import enums.EColor;

public class Advisor extends Piece {

    public Advisor(EColor color) {
        super(color);
        this.code = 1;
        this.value = Constants.ADVISOR_VALUE;
        if(color.equals(EColor.BLACK)) {
            this.code *= -1;
            this.value *= -1;
        }
        this.strCode = Constants.ADVISOR_STR_CODE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Advisor newInstance = new Advisor(this.color);
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

        Point topLeft = new Point(x - 1, y - 1);
        Point topRight = new Point(x - 1, y + 1);
        Point bottomLeft = new Point(x + 1, y - 1);
        Point bottomRight = new Point(x + 1, y + 1);

        return isValidPoints(board, new ArrayList<Point>(Arrays.asList(topLeft, topRight, bottomLeft, bottomRight)));

    }
    
}
