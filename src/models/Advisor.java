package models;

import java.util.List;

public class Advisor extends Piece {

    public Advisor(Color color) {
        super(color);
        this.code = 1;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "S";
    }


    @Override
    public List<Point> getAllPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPossibleMoves'");
    }
    
}
