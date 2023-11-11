package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import models.Advisor;
import models.Board;
import models.Canon;
import models.Chariot;
import models.Color;
import models.Elephant;
import models.General;
import models.Horse;
import models.Piece;
import models.Soldier;

public class BoardComponent extends JComponent {

    public static final String BOARD_PATH = "resources/imgs/board.png";

    public static final String ADVISOR_PATH = "resources/imgs/xiangqi_advisors.png";
    public static final String CANNON_PATH = "resources/imgs/xiangqi_cannons.png";
    public static final String CHARIOT_PATH = "resources/imgs/xiangqi_chariots.png";
    public static final String ELEPHANT_PATH = "resources/imgs/xiangqi_elephants.png";
    public static final String GENERAL_PATH = "resources/imgs/xiangqi_generals.png";
    public static final String HORSE_PATH = "resources/imgs/xiangqi_horses.png";
    public static final String SOLDIER_PATH = "resources/imgs/xiangqi_soldiers.png";

    public static int FIT_ROW_VALUE = 10;
    public static int FIT_COL_VALUE = 10;
    public static int CELL_ROW = 67;
    public static int CELL_COL = 65;

    private int panelWidth;
    private int panelHeight;
    private Board board;
    private List<BufferedImage> piecesImage;

    public BoardComponent(Board board, int panelWidth, int panelHeight) {
        this.board = board;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.piecesImage = new ArrayList<BufferedImage>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawBoard(g);
        drawPieces(g);
    }
    

    private void drawBoard(Graphics g) {
        try {
            File file = new File(BOARD_PATH);

            BufferedImage image = ImageIO.read(file);

            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            double scaleWidth = panelWidth / (double) imageWidth;
            double scaleHeight = panelHeight / (double) imageHeight;

            double scale = Math.min(scaleWidth, scaleHeight);

            int scaledWidth = (int) (image.getWidth() * scale);
            int scaledHeight = (int) (image.getHeight() * scale);

            g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawPieces(Graphics g) {
        loadPieces();
        List<Piece> piecesModel = board.getPieces();
        try {
            for (int i = 0; i < piecesImage.size(); i++) {

                BufferedImage image = piecesImage.get(i);
                Piece pieceModel = piecesModel.get(i);
    
                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();
    
                double scaleWidth = 50 / (double) imageWidth;
                double scaleHeight = 50 / (double) imageHeight;
    
                double scale = Math.min(scaleWidth, scaleHeight);
    
                int scaledWidth = (int) (imageWidth * scale);
                int scaledHeight = (int) (imageHeight * scale);

                // Reverse the position in order to same as board array in board model
                int row = (9 - pieceModel.getPoint().getX()) * CELL_ROW + FIT_ROW_VALUE;
                int col = (8 - pieceModel.getPoint().getY()) * CELL_COL + FIT_COL_VALUE;
    
                g.drawImage(image, col, row, scaledWidth, scaledHeight, null);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPieces() {
        this.piecesImage = new ArrayList<>();
        try {
            List<Piece> piecesModel = board.getPieces();
            for (Piece pieceModel : piecesModel) {
                int x, y, width, height;
                String path = "";
                if(pieceModel.getColor().equals(Color.BLACK)) {
                    x = 0;
                    y = 0;
                    width = 100;
                    height = 100;
                } else {
                    x = 200;
                    y = 0;
                    width = 100;
                    height = 100;
                }
                if(pieceModel instanceof Advisor) 
                    path = ADVISOR_PATH;
                else if(pieceModel instanceof Canon)
                    path = CANNON_PATH;                
                else if(pieceModel instanceof Chariot)
                    path = CHARIOT_PATH;
                else if (pieceModel instanceof Elephant)
                    path = ELEPHANT_PATH;
                else if (pieceModel instanceof General)
                    path = GENERAL_PATH;
                else if (pieceModel instanceof Horse)
                    path = HORSE_PATH;
                else if (pieceModel instanceof Soldier)
                    path = SOLDIER_PATH;

                BufferedImage image = ImageIO.read(new File(path)).getSubimage(x, y, width, height);
                this.piecesImage.add(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
