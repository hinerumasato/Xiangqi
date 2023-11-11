package models;

import java.util.List;

public class Soldier extends Piece {

    public Soldier(Color color) {
        super(color);
        this.code = 7;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "B";
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPossibleMoves'");
    }
    
}
