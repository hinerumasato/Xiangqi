package models;

import java.util.ArrayList;
import java.util.List;

import Constants.Constants;
import enums.EColor;

public class Canon extends Piece {

    public Canon(EColor color) {
        super(color);
        this.code = 2;
        this.value = Constants.CANNON_VALUE;
        if (color.equals(EColor.BLACK)) {
            this.code *= -1;
            this.value *= -1;
        }
        this.strCode = Constants.CANNON_STR_CODE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Canon newInstance = new Canon(this.color);
        Point p = this.getPoint();
        newInstance.setPoint(new Point(p.getX(), p.getY()));
        return newInstance;
    }

    @Override
    public List<Point> getAllPossibleMoves(Board board) {
        List<Point> result = new ArrayList<Point>();
        int x = this.getPoint().getX();
        int y = this.getPoint().getY();
        // check y for right
        for (int i = y + 1; i < Board.BOARD_COLS; i++) {
            if (Board.getInstance().isEmptyPosition(new Point(x, i)))
                result.add(new Point(x, i));
            else {
                int fuseCordX = x;
                int fuseCordY = i;
                for (int j = fuseCordY + 1; j < Board.BOARD_COLS; j++) {
                    Point point = new Point(fuseCordX, j);
                    if (!Board.getInstance().isEmptyPosition(point)) {
                        if (board.isOpponentPiece(this, point)) {
                            result.add(point);
                            break;
                        } else if (!board.isOpponentPiece(this, point))
                            break;
                    }
                }
                break;
            }
        }

        // Check y for left
        for (int i = y - 1; i >= 0; i--) {
            if (board.isEmptyPosition(new Point(x, i)))
                result.add(new Point(x, i));
            else {
                int fuseCordX = x;
                int fuseCordY = i;
                for (int j = fuseCordY - 1; j >= 0; j--) {
                    Point point = new Point(fuseCordX, j);
                    if (!Board.getInstance().isEmptyPosition(point)) {
                        if (board.isOpponentPiece(this, point)) {
                            result.add(point);
                            break;
                        } else if (!board.isOpponentPiece(this, point))
                            break;
                    }
                }
                break;
            }
        }

        // And also check x for 2 direction top and bottom
        for (int i = x + 1; i < Board.BOARD_ROWS; i++) {
            if (board.isEmptyPosition(new Point(i, y)))
                result.add(new Point(i, y));
            else {
                int fuseCordX = i;
                int fuseCordY = y;
                for (int j = fuseCordX + 1; j < Board.BOARD_ROWS; j++) {
                    Point point = new Point(j, fuseCordY);
                    if (!Board.getInstance().isEmptyPosition(point)) {
                        if (board.isOpponentPiece(this, point)) {
                            result.add(point);
                            break;
                        } else if (!board.isOpponentPiece(this, point))
                            break;
                    }
                }
                break;
            }
        }
        
        for (int i = x - 1; i >= 0; i--) {
            if (board.isEmptyPosition(new Point(i, y)))
                result.add(new Point(i, y));
            else {
                int fuseCordX = i;
                int fuseCordY = y;
                for (int j = fuseCordX - 1; j >= 0; j--) {
                    Point point = new Point(j, fuseCordY);
                    if (!Board.getInstance().isEmptyPosition(point)) {
                        if (board.isOpponentPiece(this, point)) {
                            result.add(point);
                            break;
                        } else if (!board.isOpponentPiece(this, point))
                            break;
                    }
                }
                break;
            }
        }
        return result;
    }

}
