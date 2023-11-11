package models;

import java.util.List;

public class Soldier extends Piece {

    public Soldier(Color color) {
        super(color);
        this.code = 7;
        this.strCode = "B";
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
