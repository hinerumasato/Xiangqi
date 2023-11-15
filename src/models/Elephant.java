package models;

import java.util.ArrayList;
import java.util.List;

public class Elephant extends Piece {

    public Elephant(Color color) {
        super(color);
        this.code = 4;
        if (color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "V";
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        Board board = Board.getInstance();

        Point elephantLeftTop;
        Point elephantRightTop;
        Point elephantLeftBottom;
        Point elephantRightBottom;

        if (getColor().equals(Color.RED)) {
            // check left top
            if (x - 2 >= 0 && y - 2 >= 0) {
                elephantLeftTop = new Point(x - 2, y - 2);
                if (board.isEmptyPosition(elephantLeftTop) || isOpponentPiece(elephantLeftTop)) {
                    result.add(elephantLeftTop);
                }
            }
            // check right top
            if(x-2 >=0 && y -2 < board.BOARD_COLS){
                elephantRightTop = new Point(x - 2, y + 2);
                if(board.isEmptyPosition(elephantRightTop) || isOpponentPiece(elephantRightTop)){
                    result.add(elephantRightTop);
                }
            }

        }

        return result;
    }

}
