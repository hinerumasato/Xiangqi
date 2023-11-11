package models;

import java.util.List;

public class Horse extends Piece {

    public Horse(Color color) {
        super(color);
        this.code = 6;
        this.strCode = "M";
    }

    @Override
    public boolean canMove(Point point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPossibleMoves'");
    }
    
}
