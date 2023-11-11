package models;

import java.util.List;

public class Elephant extends Piece {

    public Elephant(Color color) {
        super(color);
        this.code = 4;
        this.strCode = "V";
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
