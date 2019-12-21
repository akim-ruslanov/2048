package model;

import java.util.Random;

public class Grid {
    private int[][] grid;
    private static int gridDimension = 4;

    public Grid() {
        grid = new int[4][4];
        for (int i = 0; i < gridDimension ; i++) {
            for (int j = 0; j < gridDimension ; j++) {
                grid[i][j] = 0;
            }
        }
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
    public void moveTileUp(int row, int col) {
        if (row > 0) {
            if (grid[row][col] == grid[row-1][col] || grid[row-1][col]==0 ) {
                grid[row-1][col] += grid[row][col];
                grid[row][col] = 0;
            }
        }
    }

    public void moveUp() {
        removeSpacesUp();
        moveUpAdd();
        removeSpacesUp();
    }

    public void moveDown() {
        removeSpaceDown();
        moveDownAdd();
        removeSpaceDown();
    }

    public void moveLeft() {
        removeSpaceLeft();
        moveLeftAdd();
        removeSpaceLeft();
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
                if (grid[row][col] == 0 && row!=0) {
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
                if (grid[row][col] != 0 || grid[row][col] == grid[row][col+1]) {
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
                    grid[row][col] = grid[row][col+1];
                    grid[row][col+1] = 0;
                }
            }
        }
    }

    public void moveRightAdd() {
        for (int col = gridDimension-1; col > 0; col--) {
            for (int row = 0; row < gridDimension; row++) {
                if (grid[row][col] != 0 || grid[row][col] == grid[row][col-1]) {
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
                    grid[row][col] = grid[row][col-1];
                    grid[row][col-1] = 0;
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

    public static void main(String[] args) {
        Grid a = new Grid();
        a.displayGrid();
        a.addTile(3,0,2);
        a.addTile(3,1,2);
        a.addTile(3,2,2);
        a.addTile(3,3,2);
        a.displayGrid();
        a.moveRightAdd();
        a.removeSpaceRight();
        a.removeSpacesUp();
        a.removeSpaceDown();
        a.displayGrid();
    }
}
