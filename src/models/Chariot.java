package models;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Piece {

    public Chariot(Color color) {
        super(color);
        this.code = 3;
        this.strCode = "X";
    }

    @Override
    public boolean canMove(Point point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        Board board = Board.getInstance();
        int[][] matrix = board.getBoard();
        // Check y
        for (int i = y + 1; i < Board.BOARD_COLS; i++)
            if (matrix[x][i] == 0)
                result.add(new Point(x, i));
            else if (matrix[x][i] < 0) {
                result.add(new Point(x, i));
                break;
            } else
                break;
        // Check y for right
        for (int i = y - 1; i >= 0; i--)
            if (matrix[x][i] == 0)
                result.add(new Point(x, i));
            else if (matrix[x][i] < 0) {
                result.add(new Point(x, i));
                break;
            } else
                break;
        // Check x
        for (int i = x + 1; i < Board.BOARD_ROWS; i++)
            if (matrix[i][y] == 0)
                result.add(new Point(i, y));
            else if (matrix[i][y] < 0) {
                result.add(new Point(i, y));
                break;
            } else
                break;
        // Check x for top
        for (int i = x - 1; i >= 0; i--)
            if (matrix[i][y] == 0)
                result.add(new Point(i, y));
            else if (matrix[i][y] < 0) {
                result.add(new Point(i, y));
                break;
            } else
                break;
        return result;
    }

}
