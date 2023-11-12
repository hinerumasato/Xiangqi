package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.Board;
import models.Piece;
import models.Point;
import views.BoardComponent;

public class BoardComponentController extends MouseAdapter {

    private BoardComponent boardComponent;

    public BoardComponentController(BoardComponent boardComponent) {
        this.boardComponent = boardComponent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // In UI, X and Y will be swapped compared to X and Y in the model, so using new Point(y, x)

        int x = (int) Math.round((e.getPoint().getX() - BoardComponent.FIRST_POINT_X) / BoardComponent.CELL_COL);
        int y = (int) Math.round((e.getPoint().getY() - BoardComponent.FIRST_POINT_Y) / BoardComponent.CELL_ROW);
        Point point = new Point(y, x);
        Piece piece = Board.getInstance().getPieces().get(16);
        piece.move(point);
        boardComponent.revalidate();
        boardComponent.repaint();

    }

}
