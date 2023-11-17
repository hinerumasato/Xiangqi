package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Constants.Constants;

public class Board {


    public static final int BOARD_ROWS = 10;
    public static final int BOARD_COLS = 9;

    private int[][] board;
    private List<Piece> pieces;
    private Map<String, Piece> pieceMap;
    private static Board instance = null;

    private Board() {
        board = new int[BOARD_ROWS][BOARD_COLS];
        pieces = new ArrayList<Piece>();
        pieceMap = new HashMap<String, Piece>();

        initBlackPiecePosition();
        initRedPiecePosition();
        initBoard();
    }

    public static Board getInstance() {
        if(instance == null)
            instance = new Board();
        return instance;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void initBlackPiecePosition() {

        Piece chariot1 = new Chariot(Color.BLACK);
        Piece chariot2 = new Chariot(Color.BLACK);

        chariot1.setPoint(new Point(0, 0));
        chariot2.setPoint(new Point(0, 8));

        Piece horse1 = new Horse(Color.BLACK);
        Piece horse2 = new Horse(Color.BLACK);

        horse1.setPoint(new Point(0, 1));
        horse2.setPoint(new Point(0, 7));

        Piece elephant1 = new Elephant(Color.BLACK);
        Piece elephant2 = new Elephant(Color.BLACK);

        elephant1.setPoint(new Point(0, 2));
        elephant2.setPoint(new Point(0, 6));

        Piece advisor1 = new Advisor(Color.BLACK);
        Piece advisor2 = new Advisor(Color.BLACK);

        advisor1.setPoint(new Point(0, 3));
        advisor2.setPoint(new Point(0, 5));

        Piece general = new General(Color.BLACK);
        general.setPoint(new Point(0, 4));

        Piece cannon1 = new Canon(Color.BLACK);
        Piece cannon2 = new Canon(Color.BLACK);

        cannon1.setPoint(new Point(2, 1));
        cannon2.setPoint(new Point(2, 7));

        Piece soldier1 = new Soldier(Color.BLACK);
        Piece soldier2 = new Soldier(Color.BLACK);
        Piece soldier3 = new Soldier(Color.BLACK);
        Piece soldier4 = new Soldier(Color.BLACK);
        Piece soldier5 = new Soldier(Color.BLACK);

        soldier1.setPoint(new Point(3, 0));
        soldier2.setPoint(new Point(3, 2));
        soldier3.setPoint(new Point(3, 4));
        soldier4.setPoint(new Point(3, 6));
        soldier5.setPoint(new Point(3, 8));

        pieces.add(chariot1);
        pieces.add(chariot2);
        pieces.add(horse1);
        pieces.add(horse2);
        pieces.add(elephant1);
        pieces.add(elephant2);
        pieces.add(advisor1);
        pieces.add(advisor2);
        pieces.add(general);
        pieces.add(cannon1);
        pieces.add(cannon2);
        pieces.add(soldier1);
        pieces.add(soldier2);
        pieces.add(soldier3);
        pieces.add(soldier4);
        pieces.add(soldier5);

        pieceMap.put(Constants.BLACK_GENERAL, general);
    }

    public void initRedPiecePosition() {
        // Variables are same as initBlack but x value for each Point is 9
        Piece chariot1 = new Chariot(Color.RED);
        Piece chariot2 = new Chariot(Color.RED);

        chariot1.setPoint(new Point(9, 0));
        chariot2.setPoint(new Point(9, 8));

        Piece horse1 = new Horse(Color.RED);
        Piece horse2 = new Horse(Color.RED);

        horse1.setPoint(new Point(9, 1));
        horse2.setPoint(new Point(9, 7));

        Piece elephant1 = new Elephant(Color.RED);
        Piece elephant2 = new Elephant(Color.RED);

        elephant1.setPoint(new Point(9, 2));
        elephant2.setPoint(new Point(9, 6));

        Piece advisor1 = new Advisor(Color.RED);
        Piece advisor2 = new Advisor(Color.RED);

        advisor1.setPoint(new Point(9, 3));
        advisor2.setPoint(new Point(9, 5));

        Piece general = new General(Color.RED);
        general.setPoint(new Point(9, 4));

        Piece cannon1 = new Canon(Color.RED);
        Piece cannon2 = new Canon(Color.RED);

        cannon1.setPoint(new Point(7, 1));
        cannon2.setPoint(new Point(7, 7));

        Piece soldier1 = new Soldier(Color.RED);
        Piece soldier2 = new Soldier(Color.RED);
        Piece soldier3 = new Soldier(Color.RED);
        Piece soldier4 = new Soldier(Color.RED);
        Piece soldier5 = new Soldier(Color.RED);

        soldier1.setPoint(new Point(6, 0));
        soldier2.setPoint(new Point(6, 2));
        soldier3.setPoint(new Point(6, 4));
        soldier4.setPoint(new Point(6, 6));
        soldier5.setPoint(new Point(6, 8));

        pieces.add(chariot1);
        pieces.add(chariot2);
        pieces.add(horse1);
        pieces.add(horse2);
        pieces.add(elephant1);
        pieces.add(elephant2);
        pieces.add(advisor1);
        pieces.add(advisor2);
        pieces.add(general);
        pieces.add(cannon1);
        pieces.add(cannon2);
        pieces.add(soldier1);
        pieces.add(soldier2);
        pieces.add(soldier3);
        pieces.add(soldier4);
        pieces.add(soldier5);

        pieceMap.put(Constants.RED_GENERAL, general);
    }

    public void initBoard() {

        for(int i = 0; i < BOARD_ROWS; i++)
            for(int j = 0; j < BOARD_COLS; j++)
                board[i][j] = 0;

        for (Piece piece : pieces) {
            int row = piece.getPoint().getX();
            int col = piece.getPoint().getY();

            board[row][col] = piece.getCode();
        }
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece getPieceByPoint(Point point) {
        for(Piece piece : pieces)
            if(piece.getPoint().equals(point))
                return piece;
        return null;
    }

    public boolean isEmptyPosition(Point point) {
        return getPieceByPoint(point) == null;
    }

    public boolean isOpponentPiece(Piece piece, Point point) {
        Piece opponentPiece = getPieceByPoint(point);
        if(opponentPiece == null)
            return false;
        return !piece.getColor().equals(opponentPiece.getColor());
    }

    public void removePiece(Piece piece) {
        if(pieces.contains(piece))
            pieces.remove(piece);
    }

    public boolean isInArch(Piece piece) {
        return piece.isInArch();
    }

    public boolean isInArch(Color color, Point point) {
        int x = point.getX();
        int y = point.getY();
        if(color.equals(Color.BLACK)) {
            return y >= 3 && y <= 5 && x >= 0 && x <= 2;
        } else {
            return y >= 3 && y <= 5 && x >= 7 && x <= 9;
        }
    }

    public Map<String, Piece> getPieceMap() {
        return pieceMap;
    }

    public boolean isCheckmate(Color color) {
        Piece general;
        if(color.equals(Color.RED))
            general = pieceMap.get(Constants.RED_GENERAL);
        else general = pieceMap.get(Constants.BLACK_GENERAL);

        Point generalPoint = general.getPoint();

        for (Piece piece : pieces) {
            if(piece.getAllPossibleMoves().contains(generalPoint))
                return true;
        }

        return false;
    }

    public List<Point> getAllPossibleMovesByColor(Color color) {
        List<Point> result = new ArrayList<>();
        for(Piece piece : pieces) {
            if(piece.getColor().equals(color))
                result.addAll(piece.filterPossibleMoves());
        }

        return result;
    }


    public boolean isOver(Color color) {
        return getAllPossibleMovesByColor(color).isEmpty();
    }
}
