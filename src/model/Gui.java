package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JPanel {
    //size of a single tile/pixel
    public static final int PIXEL_SIZE = 100;

    //sizes of the board
    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 300;

    //colours
    private Color gridColor = new Color(0xEFEECF);

    //check if the game is still on
    private boolean inGame = true;

    //instance of the grid
    Grid grid = new Grid();

    public Gui() {
        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        setBackground(new Color(0xFAF8EF));
        setFont(new Font("SansSerif",Font.BOLD, 48));
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startGame();
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        grid.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        grid.moveDown();
                        break;
                    case KeyEvent.VK_RIGHT:
                        grid.moveRight();
                        break;
                    case KeyEvent.VK_LEFT:
                        grid.moveLeft();
                        break;
                }
                repaint();
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(gridColor);

    }


    private void startGame() {

    }


}
