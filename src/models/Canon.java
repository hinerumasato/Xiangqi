package models;

import java.util.ArrayList;
import java.util.List;

public class Canon extends Piece {

    public Canon(Color color) {
        super(color);
        this.code = 2;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "P";
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        Board board = Board.getInstance();
        int[][] matrix = board.getBoard();
        // check y for right
        for (int i = y + 1; i < Board.BOARD_COLS; i++) {
            if (matrix[x][i] == 0)
                result.add(new Point(x, i));
            else {
                int fuseCordX = x;
                int fuseCordY = i;
                for (int j = fuseCordY + 1; j < Board.BOARD_COLS; j++) {
                    if (matrix[fuseCordX][j] < 0) {
                        result.add(new Point(fuseCordX, j));
                        break;
                    } else if (matrix[fuseCordX][j] > 0)
                        break;
                }
                break;
            }
        }

        // Check y for left
        for (int i = y - 1; i >= 0; i--) {
            if (matrix[x][i] == 0)
                result.add(new Point(x, i));
            else {
                int fuseCordX = x;
                int fuseCordY = i;
                for (int j = fuseCordY - 1; j >= 0; j--) {
                    if (matrix[fuseCordX][j] < 0) {
                        result.add(new Point(fuseCordX, j));
                        break;
                    } else if (matrix[j][fuseCordY] > 0)
                        break;
                }
                break;
            }
        }

        // And also check x for 2 direction top and bottom
        for (int i = x + 1; i < Board.BOARD_ROWS; i++) {
            if (matrix[i][y] == 0)
                result.add(new Point(i, y));
            else {
                int fuseCordX = i;
                int fuseCordY = y;
                for (int j = fuseCordX + 1; j < Board.BOARD_COLS; j++) {
                    if (matrix[j][fuseCordY] < 0) {
                        result.add(new Point(j, fuseCordY));
                        break;
                    } else if (matrix[j][fuseCordY] > 0)
                        break;
                }
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (matrix[i][y] == 0)
                result.add(new Point(i, y));
            else {
                int fuseCordX = i;
                int fuseCordY = y;
                for (int j = fuseCordX - 1; j >= 0; j--) {
                    if (matrix[j][fuseCordY] < 0) {
                        result.add(new Point(j, fuseCordY));
                        break;
                    } else if (matrix[j][fuseCordY] > 0)
                        break;
                }
                break;
            }
        }
        return result;
    }

}
