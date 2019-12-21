package model;

import javax.swing.*;

public class Gui extends JPanel {
    //size of a single tile/pixel
    public static final int PIXEL_SIZE = 100;

    //sizes of the board
    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 300;

    //check if the game is still on
    private boolean inGame = true;

    //instance of the grid
    Grid grid = new Grid();

    public Gui() {

    }
}
