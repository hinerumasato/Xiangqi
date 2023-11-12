package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.Board;
import models.Piece;
import models.Point;
import views.BoardComponent;

public class BoardComponentController extends MouseAdapter {

    private BoardComponent boardComponent;
    private boolean isSelected;

    public BoardComponentController(BoardComponent boardComponent) {
        this.boardComponent = boardComponent;
        this.isSelected = false;
    }

    private Point calculatePoint(MouseEvent e) {
        // In UI, X and Y will be swapped compared to X and Y in the model, so using new Point(y, x)
        int x = (int) Math.round((e.getPoint().getX() - BoardComponent.FIRST_POINT_X) / BoardComponent.CELL_COL);
        int y = (int) Math.round((e.getPoint().getY() - BoardComponent.FIRST_POINT_Y) / BoardComponent.CELL_ROW);
        Point point = new Point(y, x);
        return point;
    } 

    @Override
    public void mouseClicked(MouseEvent e) {

        Board board = Board.getInstance();

        
        if(!isSelected) {
            Point point = calculatePoint(e);
            if(!board.isEmptyPosition(point)) {
                Piece piece = board.getPieceByPoint(point);
                boardComponent.setSelectedPiece(piece);
                isSelected = true;
            }
        } else {
            Point point = calculatePoint(e);
            Piece piece = boardComponent.getSelectedPiece();
            piece.move(point);
            boardComponent.setSelectedPiece(null);
            isSelected = false;
        }
        boardComponent.revalidate();
        boardComponent.repaint();

    }

}
