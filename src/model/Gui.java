package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static model.Grid.gridDimension;

public class Gui extends JPanel {
    //size of a single tile/pixel
    public static final int SIZE = 200;

    //sizes of the board
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    //colours
    private Color gridColor = new Color(0xEFEECF);
    private Color emptyColor = new Color(0xcdc0b4);
    final Color[] colorTable = {
            new Color(0xede4da), new Color(0xede4da), new Color(0xf3b179),
            new Color(0xf59563), new Color(0xf57c5f), new Color(0xe95937),
            new Color(0xf2d86a), new Color(0xe6bf2c), new Color(0xe2b913),
            new Color(0xe6c65c), new Color(0xe6c65c), new Color(0x701710)};

    final Color[] fontColors = {new Color(0x776d62), new Color(0xfbfdfa)};

    //check if the game is still on
    private boolean inGame = true;

    //instance of the grid
    Grid grid = new Grid();


    static Robot robot;

    public Gui() {
        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        setBackground(new Color(0xFB938A80, true));
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
        drawGrid(g2);
    }

    private void drawGrid(Graphics2D g) {
        g.setColor(gridColor);
        g.fillRoundRect(0,0,800,800,15,15);
        int[][] gridArray = grid.getGrid();
        if (inGame) {
            for (int r = 0; r < gridDimension; r++) {
                for (int c = 0; c < gridDimension; c++) {
                    if (gridArray[r][c] == 0) {
                        g.setColor(emptyColor);
                        g.fillRoundRect(c*SIZE+5,r*SIZE+5,SIZE-10,SIZE-10,15,15);
                    } else {
                        drawTile(g,r,c, gridArray[r][c]);
                    }
                }
            }
        }
        //TODO: finish end game situation


    }

    private void drawTile(Graphics2D g, int r, int c, int value) {
        int colorPos = (int) (Math.log(value)/Math.log(2));
        g.setColor(colorTable[colorPos]);
        g.fillRoundRect(c*SIZE+5,r*SIZE+5, SIZE-10, SIZE-10,15,15);
        String s = String.valueOf(value);
        g.setColor(value < 128 ? fontColors[0] : fontColors[1]);
        FontMetrics fm = g.getFontMetrics();
        int x = c*SIZE + (SIZE/2)-10-(fm.stringWidth(s))/4;
        int y  = r*SIZE + (SIZE/2) + 10;
        g.drawString(s,x,y);
    }


    private void startGame() {

    }

    public static void main(String[] args) throws AWTException {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("2048");
            f.setResizable(true);
            f.add(new Gui(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        while (true) {
            int move = random.nextInt(4);
            System.out.println(move);
            switch (move) {
                case 0:
                    robot.keyPress(KeyEvent.VK_UP);
                    break;
                case 1:
                    robot.keyPress(KeyEvent.VK_DOWN);
                    break;
                case 2:
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    break;
                case 3:
                    robot.keyPress(KeyEvent.VK_LEFT);
                    break;
            }
            robot.delay(50);
            }
        }
}

