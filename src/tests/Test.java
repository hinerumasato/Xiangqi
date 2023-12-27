package tests;

import AI.Heuristic;
import Constants.Constants;
import models.Board;
import models.Piece;
import models.Point;
import utils.ArrayUtil;

public class Test {

    static Board board = Board.getInstance();

    public static void testCloneBoard() throws CloneNotSupportedException {
        Board newBoard = (Board) board.clone();

        System.out.println("----------------- Board Address Location ----------------------");
        System.out.println(board);
        System.out.println(newBoard);
        System.out.println("------------------- List pieces Address Location --------------");
        System.out.println(Integer.toHexString(board.getPieces().hashCode()));
        System.out.println(Integer.toHexString(newBoard.getPieces().hashCode()));
        System.out.println("----------------- Hashmap piece Address Location --------------");
        System.out.println(board.getPieceMap());
        System.out.println(newBoard.getPieceMap());
        System.out.println("----------------- Piece's Point Address Location --------------");
        System.out.println(Integer.toHexString(board.getPieceMap().get(Constants.RED_GENERAL).getPoint().hashCode()));
        System.out.println(Integer.toHexString(newBoard.getPieceMap().get(Constants.RED_GENERAL).getPoint().hashCode()));
    }

    public static void testAdvisor() {
        Piece redAdvisor = board.getPieceByPoint(new Point(9, 3));
        System.out.println(redAdvisor.getAllPossibleMoves(board));
    }

    public static void testChariot() {
        Piece chariot = board.getPieces().get(16);
        System.out.println(chariot.getAllPossibleMoves(board));

    }

    public static void testCannon() {
        Piece cannon = board.getPieces().get(25);
        System.out.println(cannon.getAllPossibleMoves(board));
    }

    public static void testGeneral() {
        Piece general = board.getPieceByPoint(new Point(9, 4));
        System.out.println(board.isInArch(general));
    }

    public static void testElephant(){
        Piece elephant = board.getPieces().get(4);
        System.out.println(elephant.getAllPossibleMoves(board));
    }

    public static void testIsCheckmateAfterMove() {
        Piece redCannon = board.getPieceByPoint(new Point(7, 7));
        redCannon.setPoint(new Point(3, 8));
    }

    public static void testArrayUtilReflect() {
        int[][] matrix = new int[][] {
            new int[] {1, 2, 3},
            new int[] {4, 5, 6},
            new int[] {7, 8, 9},
        };

        ArrayUtil.printMatrix(ArrayUtil.reflect(matrix));
    }

    public static void testHeuristic() {
        Board board = Board.getInstance();
        System.out.println(Heuristic.compute(board));
    }
    public static void main(String[] args) throws Exception {
        testHeuristic();
    }
}
