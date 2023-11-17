package tests;

import models.Board;
import models.Piece;
import models.Point;

public class Test {

    static Board board = Board.getInstance();

    public static void testAdvisor() {
        Piece redAdvisor = board.getPieceByPoint(new Point(9, 3));
        System.out.println(redAdvisor.getAllPossibleMoves());
    }

    public static void testChariot() {
        Piece chariot = board.getPieces().get(16);
        System.out.println(chariot.getAllPossibleMoves());

    }

    public static void testCannon() {
        Piece cannon = board.getPieces().get(25);
        System.out.println(cannon.getAllPossibleMoves());
    }

    public static void testGeneral() {
        Piece general = board.getPieceByPoint(new Point(9, 4));
        System.out.println(board.isInArch(general));
    }

    public static void testElephant(){
        Piece elephant = board.getPieces().get(4);
        System.out.println(elephant.getAllPossibleMoves());
    }

    public static void main(String[] args) {
        testElephant();
    }
}
