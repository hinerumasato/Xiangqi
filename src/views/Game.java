package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Board;

public class Game extends JFrame {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final String TITLE = "Xiangqi";

    private JPanel leftPanel;
    private JPanel rightPanel;
    private Board board;

    public Game() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        this.board = Board.getInstance();

        setLayout(new GridLayout(1, 2));

        
        BoardComponent boardComponent = new BoardComponent(board, Game.WINDOW_WIDTH / 2, Game.WINDOW_HEIGHT);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(boardComponent, BorderLayout.CENTER);

        leftPanel.setBorder(BorderFactory.createTitledBorder("Xiangqi"));

        getContentPane().add(leftPanel);
        getContentPane().add(rightPanel);

        revalidate();
        repaint();

    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

}
