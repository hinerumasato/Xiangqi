package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class General extends Piece {

    public General(Color color) {
        super(color);
        this.code = 5;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "T";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        General newInstance = new General(this.color);
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

        Point top = new Point(x + 1, y);
        Point bottom = new Point(x -1 , y);
        Point left = new Point(x, y - 1);
        Point right = new Point(x, y + 1);

        return isValidPoints(new ArrayList<Point>(Arrays.asList(top, bottom, left, right)));

    }
    
}
