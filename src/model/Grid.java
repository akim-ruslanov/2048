package model;

import java.util.Random;
import java.util.Scanner;

public class Grid {
    private int[][] grid;
    private int[][] oldGrid;
    public static int gridDimension = 4;
    private boolean moveRight = true;
    private boolean moveLeft = true;
    private boolean moveUp = true;
    private boolean moveDown = true;



    public Grid() {
        grid = new int[gridDimension][gridDimension];
        oldGrid = new int[gridDimension][gridDimension];
        for (int i = 0; i < gridDimension ; i++) {
            for (int j = 0; j < gridDimension ; j++) {
                grid[i][j] = 0;
            }
        }
        addTile(0,0,2);
        addTile(0,1,4);

        //uncomment to add random tiles
//        Random random = new Random();
//        for (int i = 0; i < 2; ) {
//            int row = random.nextInt(gridDimension);
//            int col = random.nextInt(gridDimension);
//            if (available(row, col)) {
//                grid[row][col] = 2;
//                i++;
//            }
//        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean available(int row, int col) {
        return grid[row][col] == 0;
    }

    public void addRandomTile() {
        Random random = new Random();
        int row = random.nextInt(gridDimension);
        int col = random.nextInt(gridDimension);
        if (available(row, col)) {
            grid[row][col] = 2;
        } else
            addRandomTile();
    }

    public void addTile(int row, int col, int value) {
        if (available(row, col)) {
            grid[row][col] = value;
        } else {
            System.out.println("The tile location you have chose is occupied");
        }
    }


    public void moveUp() {
        recordOldGrid();
        removeSpacesUp();
        moveUpAdd();
        removeSpacesUp();
        if (compareArrays(grid, oldGrid)) {
            moveUp = false;
        } else {
            moveUp = true;
            addRandomTile();
        }

    }

    public void moveDown() {
        recordOldGrid();
        removeSpaceDown();
        moveDownAdd();
        removeSpaceDown();
        if (compareArrays(grid, oldGrid)) {
            moveDown = false;
        } else {
            moveDown = true;
            addRandomTile();
        }
    }

    public void moveLeft() {
        recordOldGrid();
        removeSpaceLeft();
        moveLeftAdd();
        removeSpaceLeft();
        if (compareArrays(grid, oldGrid)) {
            moveLeft = false;
        } else {
            moveLeft = true;
            addRandomTile();
        }
    }

    public void moveRight() {
        recordOldGrid();
        removeSpaceRight();
        moveRightAdd();
        removeSpaceRight();
        if (compareArrays(grid, oldGrid)) {
            moveRight = false;
        } else {
            moveRight = true;
            addRandomTile();
        }
    }

    public void moveUpAdd() {
        for (int row = 0; row < gridDimension-1; row++) {
            for (int col = 0; col < gridDimension; col++) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row + 1][col]) {
                    grid[row][col] += grid[row + 1][col];
                    grid[row + 1][col] = 0;
                }
            }
        }
    }

    public void removeSpacesUp() {
        for (int row = 0; row < gridDimension-1; row++) {
            for (int col = 0; col < gridDimension; col++) {
                if (grid[row][col] == 0 && row != gridDimension-1) {
                    for (int r = row; r < gridDimension; r++) {
                        if (grid[r][col] != 0) {
                            grid[row][col] = grid[r][col];
                            grid[r][col] = 0;
                            r = gridDimension;
                        }
                    }
                }
            }
        }
    }

    public void moveDownAdd() {
        for (int row = gridDimension-1; row > 0; row --) {
            for (int col = 0; col < gridDimension ; col++) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row-1][col]) {
                    grid[row][col] += grid[row-1][col];
                    grid[row-1][col] = 0;
                }
            }
        }
    }

    public void removeSpaceDown() {
        for (int row = gridDimension-1; row > 0; row--) {
            for (int col = 0; col < gridDimension; col++) {
                if (grid[row][col] == 0) {
                    for  (int r = row; r >= 0; r--) {
                        if (grid[r][col] !=0) {
                            grid[row][col] = grid[r][col];
                            grid[r][col] = 0;
                            r = -1;
                        }
                    }
                }
            }
        }
    }

    public void moveLeftAdd() {
        for (int col = 0; col < gridDimension-1; col++) {
            for (int row = 0; row < gridDimension; row++) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row][col+1]) {
                    grid[row][col] += grid[row][col+1];
                    grid[row][col+1] = 0;
                }
            }
        }
    }

    public void removeSpaceLeft() {
        for (int col = 0; col < gridDimension-1; col++) {
            for (int row = 0; row < gridDimension; row++) {
                if (grid[row][col] == 0) {
                    for  (int c = col; c < gridDimension; c++) {
                        if (grid[row][c] !=0) {
                            grid[row][col] = grid[row][c];
                            grid[row][c] = 0;
                            c = gridDimension;
                        }
                    }
                }
            }
        }
    }

    public void moveRightAdd() {
        for (int col = gridDimension-1; col > 0; col--) {
            for (int row = 0; row < gridDimension; row++) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row][col-1]) {
                    grid[row][col] += grid[row][col-1];
                    grid[row][col-1] = 0;
                }
            }
        }
    }

    public void removeSpaceRight() {
        for (int col = gridDimension-1; col > 0; col --) {
            for (int row = 0; row < gridDimension; row++) {
                if (grid[row][col]==0) {
                    for  (int c = col; c >= 0; c--) {
                        if (grid[row][c] !=0) {
                            grid[row][col] = grid[row][c];
                            grid[row][c] = 0;
                            c = -1;
                        }
                    }
                }
            }
        }
    }

    public void displayGrid() {
        System.out.println("============");
        int row = 0;
        while (row < gridDimension) {
            int col = 0;
            while (col < gridDimension - 1) {
                System.out.print(grid[row][col]);
                col++;
            }
            System.out.println(grid[row][col]);
            row++;
        }
        System.out.println("=================");
    }

    public void recordOldGrid() {
        for (int r = 0; r < gridDimension; r++) {
            for (int c = 0; c < gridDimension; c++) {
                oldGrid[r][c] = grid[r][c];
            }
        }
    }

    public boolean compareArrays(int[][] a1, int[][] a2) {
        for (int r = 0; r < gridDimension; r++) {
            for (int c = 0; c <gridDimension; c ++) {
                if (a1[r][c] != a2[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean endGame() {
        return moveRight && moveLeft && moveUp && moveDown;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.displayGrid();
        Scanner input = new Scanner(System.in);
        String move = input.next();
        while (!move.equals("n")) {
            if (move.equals("u")) {
                grid.moveUp();
                grid.displayGrid();
            } else if (move.equals("r")) {
                grid.moveRight();
                grid.displayGrid();
            } else if (move.equals("l")) {
                grid.moveLeft();
                grid.displayGrid();
            } else if (move.equals("d")) {
                grid.moveDown();
                grid.displayGrid();
            }
            move = input.next();
        }
//        Grid g = new Grid();
//        g.addTile(0,0,2);
//        g.addTile(0,1,2);
//        g.addTile(0,2,4);
//        g.addTile(0,3,2);
//        g.displayGrid();
//        g.moveLeftAdd();
//        g.displayGrid();
    }
}
