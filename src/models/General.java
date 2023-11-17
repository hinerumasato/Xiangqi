package models;

import java.util.List;

public class General extends Piece {

    public General(Color color) {
        super(color);
        this.code = 5;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "T";
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPossibleMoves'");
    }
    
}
