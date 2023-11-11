package tests;

import models.Board;
import models.Piece;

public class Test {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        Piece chariot = board.getPieces().get(16);
        Piece cannon = board.getPieces().get(25);
        System.out.println(cannon.getAllPossibleMoves());


    }
}
