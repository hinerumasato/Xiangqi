package models;

import java.util.List;

public class Horse extends Piece {

    public Horse(Color color) {
        super(color);
        this.code = 6;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "M";
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPossibleMoves'");
    }
    
}
