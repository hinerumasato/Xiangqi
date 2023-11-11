package models;

import java.util.List;

public class Advisor extends Piece {

    public Advisor(Color color) {
        super(color);
        this.code = 1;
        this.strCode = "S";
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
