package tests;

import models.Board;
import models.Piece;
import models.Point;

public class Test {

    static Board board = Board.getInstance();

    public static void testCloneBoard() throws CloneNotSupportedException {
        Board newBoard = (Board) board.clone();

        System.out.println("----------------- Board Address Location ---------------------");
        System.out.println(board);
        System.out.println(newBoard);
        System.out.println("----------------- List piece Address Location ------------------");
        System.out.println(board.getPieces());
        System.out.println(newBoard.getPieces());
    }

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

    public static void testIsCheckmateAfterMove() {
        Piece redCannon = board.getPieceByPoint(new Point(7, 7));
        redCannon.setPoint(new Point(3, 8));
    }

<<<<<<< HEAD
    public static void main(String[] args) throws Exception {
        testCloneBoard();
=======
    public static void main(String[] args) {
        testElephant();
        Board board = Board.getInstance();
        System.out.println(Board.BOARD_COLS);
        System.out.println(Board.BOARD_ROWS / 2);
>>>>>>> 7a8068a7ce064351f3c796a5be23f407bc9d6ab2
    }
}
