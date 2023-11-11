package models;

import java.util.List;

public class Canon extends Piece {

    public Canon(Color color) {
        super(color);
        this.code = 2;
        this.strCode = "P";
    }

    @Override
    public boolean canMove(Point point) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }

    @Override
    public List<Point> getAllPossibleMoves() {
        List<Point> result = new ArrayList<Point>();
        return result;
    }
    
}
