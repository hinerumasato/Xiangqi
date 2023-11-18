package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Advisor extends Piece {

    public Advisor(Color color) {
        super(color);
        this.code = 1;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "S";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Advisor newInstance = new Advisor(this.color);
        Point p = this.getPoint();
        newInstance.setPoint(new Point(p.getX(), p.getY()));
        return newInstance;
    }

    private List<Point> isValidPoints(List<Point> points) {
        Board board = Board.getInstance();
        List<Point> result = new ArrayList<Point>();
        for (Point point : points) {
            if(board.isInArch(getColor(), point) && (board.isEmptyPosition(point) || board.isOpponentPiece(this, point)))
                result.add(point);
        }
        return result;
    }


    @Override
    public List<Point> getAllPossibleMoves() {
        int x = point.getX();
        int y = point.getY();

        Point topLeft = new Point(x - 1, y - 1);
        Point topRight = new Point(x - 1, y + 1);
        Point bottomLeft = new Point(x + 1, y - 1);
        Point bottomRight = new Point(x + 1, y + 1);

        return isValidPoints(new ArrayList<Point>(Arrays.asList(topLeft, topRight, bottomLeft, bottomRight)));

    }
    
}
