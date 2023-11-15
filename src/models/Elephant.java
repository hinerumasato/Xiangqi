package models;

import java.util.ArrayList;
import java.util.List;

public class Elephant extends Piece {

    public Elephant(Color color) {
        super(color);
        this.code = 4;
        if(color.equals(Color.BLACK))
            this.code *= -1;
        this.strCode = "V";
    }
    
    private boolean isOverLake(Point point) {
        int x = point.getX();
        if (getColor().equals(Color.RED)) {
            return x > 0 && x <= 4;
        } else
            return x >= 5 && x <= 9;
    }


    @Override
    public List<Point> getAllPossibleMoves() {
       List<Point> result = new ArrayList<Point>();
       int x = this.getPoint().getX();
       int y = this.getPoint().getY();
       Board board = Board.getInstance();
    
       Point lefttop;
       Point righttop;
       Point leftbottom;
       Point rightbottom;  
    
       if(getColor().equals(Color.RED)){
            for(int i = x +1; i <x +3; i++){
                lefttop = new Point(i, i)
            }
       }

       return result;
    }
    
}
