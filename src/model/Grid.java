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
        Random random = new Random();
        for (int i = 0; i < 2; ) {
            int row = random.nextInt(gridDimension);
            int col = random.nextInt(gridDimension);
            if (available(row, col)) {
                grid[row][col] = 2;
                i++;
            }
        }
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

    public void moveTileUp(int row, int col) {
        if (row > 0) {
            if (grid[row][col] == grid[row-1][col] || grid[row-1][col]==0 ) {
                grid[row-1][col] += grid[row][col];
                grid[row][col] = 0;
            }
        }
    }


    //EFFECTS: moves all non zero tiles in the row up
    public void fillUpEmptyTilesUpCol(int col) {
        int emptySpace;
        for (int i = 0; i < gridDimension-1; i++) {
            if (grid[i][col] == 0) {
                emptySpace = i;
                for (int j = i+1; j<gridDimension; j++) {
                    if (grid[j][col] == 1) {
                        grid[emptySpace][col] = grid[j][col];
                        grid[j][col] = 0;
                    }
                }
            }
        }
    }

    public void moveUp() {
        for (int i = 0; i < gridDimension-1; i++) {
            for (int j = 0; j < gridDimension; j++) {

                moveTileUp(i+1,j);
            }
        }
    }

    public static void main(String[] args) {
        Grid a = new Grid();
        int row = 0;
        while (row < gridDimension) {
            int col = 0;
            while (col < gridDimension - 1) {
                System.out.print(a.grid[row][col]);
                col++;
            }
            System.out.println(a.grid[row][col]);
            row++;
        }
        System.out.println("================");
        a.addRandomTile();
        row = 0;
        while (row < gridDimension) {
            int col = 0;
            while (col < gridDimension - 1) {
                System.out.print(a.grid[row][col]);
                col++;
            }
            System.out.println(a.grid[row][col]);
            row++;
        }
        System.out.println("=============");
        a.moveUp();
        row = 0;
        while (row < gridDimension) {
            int col = 0;
            while (col < gridDimension - 1) {
                System.out.print(a.grid[row][col]);
                col++;
            }
            System.out.println(a.grid[row][col]);
            row++;
        }
    }
}
