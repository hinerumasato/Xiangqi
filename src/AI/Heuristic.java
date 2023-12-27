package AI;

import java.util.List;

import enums.EColor;
import models.Advisor;
import models.Board;
import models.Canon;
import models.Chariot;
import models.Elephant;
import models.Horse;
import models.Piece;
import models.Point;
import models.Soldier;
import utils.ArrayUtil;

public class Heuristic {
    private static final int[][] CANNON_POSITION = new int[][] {
        new int[] { 0, 0, 2, 6, 6, 6, 2, 0, 0 },
        new int[] { 0, 2, 4, 6, 6, 6, 4, 2, 0 },
        new int[] { 3, 0, 8, 6, 12, 6, 8, 0, 3 },
        new int[] { 0, 0, 0, 2, 4, 2, 0, 0, 0 },
        new int[] { -2, 0, 4, 2, 6, 2, 4, 0, -2 },
        new int[] { 0, 0, 0, 2, 8, 2, 0, 0, 0 },
        new int[] { 0, 0, -2, 4, 10, 4, -2, 0, 0 },
        new int[] { 2, 2, 0, -10, -8, -10, 0, 2, 2 },
        new int[] { 2, 2, 0, -4, -14, -4, 0, 2, 2 },
        new int[] { 6, 4, 0, -10, -12, -10, 0, 4, 6 },
    };

    private static final int[][] CHARIOT_POSITION = new int[][] {
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, -2, 0, 4, 0, -2, 0, 0 },
        new int[] { 2, 0, 8, 0, 8, 0, 8, 0, 2 },
        new int[] { 6, 12, 18, 18, 20, 18, 18, 12, 6 },
        new int[] { 10, 20, 30, 34, 40, 34, 30, 20, 10 },
        new int[] { 14, 26, 42, 60, 80, 60, 42, 26, 14 },
        new int[] { 18, 36, 56, 80, 120, 80, 56, 36, 18 },
        new int[] { 0, 3, 6, 9, 12, 9, 6, 3, 0 }
    };

    private static final int[][] HORSE_POSITION = new int[][] {
        new int[] { 0, -4, 0, 0, 0, 0, 0, -4, 0 },
        new int[] { 0, 2, 4, 4, -2, 4, 4, 2, 0 },
        new int[] { 4, 2, 8, 8, 4, 8, 8, 2, 4 },
        new int[] { 2, 6, 8, 6, 10, 6, 8, 6, 2 },
        new int[] { 4, 12, 16, 14, 12, 14, 16, 12, 4 },
        new int[] { 6, 16, 14, 18, 16, 18, 14, 16, 6 },
        new int[] { 8, 24, 18, 24, 20, 24, 18, 24, 8 },
        new int[] { 12, 14, 16, 20, 18, 20, 16, 14, 12 },
        new int[] { 4, 10, 28, 16, 8, 16, 28, 10, 4 },
        new int[] { 4, 8, 16, 12, 4, 12, 16, 8, 4 }
    };

    private static final int[][] SOLDIER_POSITION = new int[][] {
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, -2, 0, 4, 0, -2, 0, 0 },
        new int[] { 2, 0, 8, 0, 8, 0, 8, 0, 2 },
        new int[] { 6, 12, 18, 18, 20, 18, 18, 12, 6 },
        new int[] { 10, 20, 30, 34, 40, 34, 30, 20, 10 },
        new int[] { 14, 26, 42, 60, 80, 60, 42, 26, 14 },
        new int[] { 18, 36, 56, 80, 120, 80, 56, 36, 18 },
        new int[] { 0, 3, 6, 9, 12, 9, 6, 3, 0 }
    };

    private static final int[][] ELEPHANT_POSITION = new int[][] {
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 3, 0, 0, 0, 10, 0, 0, 0, 3 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 5, 0, 0, 0, 5, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    private static final int[][] ADVISOR_POSITION = new int[][] {
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 10, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 8, 0, 8, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    public static int[][] getBlackCannonPositions() {
        return ArrayUtil.multiplyByMinusOne(CANNON_POSITION);
    }

    public static int[][] getBlackChariotPositions() {
        return ArrayUtil.multiplyByMinusOne(CHARIOT_POSITION);
    }

    public static int[][] getBlackHorsePositions() {
        return ArrayUtil.multiplyByMinusOne(HORSE_POSITION);
    }

    public static int[][] getBlackSoldierPositions() {
        return ArrayUtil.multiplyByMinusOne(SOLDIER_POSITION);
    }

    public static int[][] getBlackElephantPositions() {
        return ArrayUtil.multiplyByMinusOne(ELEPHANT_POSITION);
    }

    public static int[][] getBlackAdvisorPositions() {
        return ArrayUtil.multiplyByMinusOne(ADVISOR_POSITION);
    }

    public static int[][] getRedCannonPositions() {
        return ArrayUtil.reflect(CANNON_POSITION);
    }

    public static int[][] getRedChariotPositions() {
        return ArrayUtil.reflect(CHARIOT_POSITION);
    }

    public static int[][] getRedHorsePositions() {
        return ArrayUtil.reflect(HORSE_POSITION);
    }

    public static int[][] getRedSoldierPositions() {
        return ArrayUtil.reflect(SOLDIER_POSITION);
    }

    public static int[][] getRedElephantPositions() {
        return ArrayUtil.reflect(ELEPHANT_POSITION);
    }

    public static int[][] getRedAdvisorPositions() {
        return ArrayUtil.reflect(ADVISOR_POSITION);
    }

    public static int getBlackCannonDetailValueByPoint(int row, int col) {
        return getBlackCannonPositions()[row][col];
    }

    public static int getBlackCannonDetailValueByPoint(Point p) {
        return getBlackCannonPositions()[p.getX()][p.getY()];
    }

    public static int getBlackChariotDetailValueByPoint(int row, int col) {
        return getBlackChariotPositions()[row][col];
    }

    public static int getBlackChariotDetailValueByPoint(Point p) {
        return getBlackChariotPositions()[p.getX()][p.getY()];
    }

    public static int getBlackHorseDetailValueByPoint(int row, int col) {
        return getBlackHorsePositions()[row][col];
    }

    public static int getBlackHorseDetailValueByPoint(Point p) {
        return getBlackHorsePositions()[p.getX()][p.getY()];
    }
    
    public static int getBlackSoldierDetailValueByPoint(int row, int col) {
        return getBlackSoldierPositions()[row][col];
    }

    public static int getBlackSoldierDetailValueByPoint(Point p) {
        return getBlackSoldierPositions()[p.getX()][p.getY()];
    }

    public static int getBlackElephantDetailValueByPoint(int row, int col) {
        return getBlackElephantPositions()[row][col];
    }

    public static int getBlackElephantDetailValueByPoint(Point p) {
        return getBlackElephantPositions()[p.getX()][p.getY()];
    }

    public static int getBlackAdvisorDetailValueByPoint(int row, int col) {
        return getBlackAdvisorPositions()[row][col];
    }

    public static int getBlackAdvisorDetailValueByPoint(Point p) {
        return getBlackAdvisorPositions()[p.getX()][p.getY()];
    }

    public static int getRedCannonDetailValueByPoint(int row, int col) {
        return getRedCannonPositions()[row][col];
    }

    public static int getRedCannonDetailValueByPoint(Point p) {
        return getRedCannonPositions()[p.getX()][p.getY()];
    }

    public static int getRedChariotDetailValueByPoint(int row, int col) {
        return getRedChariotPositions()[row][col];
    }

    public static int getRedChariotDetailValueByPoint(Point p) {
        return getRedChariotPositions()[p.getX()][p.getY()];
    }

    public static int getRedHorseDetailValueByPoint(int row, int col) {
        return getRedHorsePositions()[row][col];
    }

    public static int getRedHorseDetailValueByPoint(Point p) {
        return getRedHorsePositions()[p.getX()][p.getY()];
    }
    
    public static int getRedSoldierDetailValueByPoint(int row, int col) {
        return getRedSoldierPositions()[row][col];
    }

    public static int getRedSoldierDetailValueByPoint(Point p) {
        return getRedSoldierPositions()[p.getX()][p.getY()];
    }

    public static int getRedElephantDetailValueByPoint(int row, int col) {
        return getRedElephantPositions()[row][col];
    }

    public static int getRedElephantDetailValueByPoint(Point p) {
        return getRedElephantPositions()[p.getX()][p.getY()];
    }

    public static int getRedAdvisorDetailValueByPoint(int row, int col) {
        return getRedAdvisorPositions()[row][col];
    }

    public static int getRedAdvisorDetailValueByPoint(Point p) {
        return getRedAdvisorPositions()[p.getX()][p.getY()];
    }

    public static int getValueByPiece(Piece piece) {
        EColor color = piece.getColor();
        Point point = piece.getPoint();

        if(color.equals(EColor.BLACK)) {
            if(piece instanceof Canon)
                return getBlackCannonDetailValueByPoint(point);
            if(piece instanceof Chariot)
                return getBlackChariotDetailValueByPoint(point);
            if(piece instanceof Horse)
                return getBlackHorseDetailValueByPoint(point);
            if(piece instanceof Soldier)
                return getBlackSoldierDetailValueByPoint(point);
            if(piece instanceof Elephant)
                return getBlackElephantDetailValueByPoint(point);
            if(piece instanceof Advisor)
                return getBlackAdvisorDetailValueByPoint(point);
        } else {
            if(piece instanceof Canon)
                return getRedCannonDetailValueByPoint(point);
            if(piece instanceof Chariot)
                return getRedChariotDetailValueByPoint(point);
            if(piece instanceof Horse)
                return getRedHorseDetailValueByPoint(point);
            if(piece instanceof Soldier)
                return getRedSoldierDetailValueByPoint(point);
            if(piece instanceof Elephant)
                return getRedElephantDetailValueByPoint(point);
            if(piece instanceof Advisor)
                return getRedAdvisorDetailValueByPoint(point);
        }
        return 0;
    }

    public static int compute(Board board) {
        List<Piece> pieces = board.getPieces();
        int sum = 0;
        sum += pieces.stream().mapToInt(piece -> piece.getValue()).sum();
        sum += pieces.stream().mapToInt(piece -> getValueByPiece(piece)).sum();

        // if(board.isOver(EColor.RED)) return Integer.MAX_VALUE;
        // if(board.isOver(EColor.BLACK)) return Integer.MIN_VALUE;

        return sum;
    }
}
