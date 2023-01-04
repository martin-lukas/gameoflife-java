package org.lukas.gameoflife.domain;

import java.util.List;

public class Game {
    private final int width;
    private final int height;
    private final boolean[][] cells;

    /**
     * Constructor for the game class.
     * @param width width of the game grid (number of columns)
     * @param height height of the game grid (number of rows)
     * @param input coordinates of cells alive at the beginning of the game's run
    */
    public Game(int width, int height, List<Cell> input) {
        this.width = width;
        this.height = height;
        validateInput(input);
        this.cells = populateCells(width, height, input);
    }

    private void validateInput(List<Cell> input) {
        boolean isWithinBounds = input.stream()
                .allMatch(cell -> cell.x() < width && cell.y() < height);
        if (!isWithinBounds) {
            throw new IllegalArgumentException("The input cells' coordinates are outside of grid bounds");
        }
    }

    private boolean[][] populateCells(int width, int height, List<Cell> input) {
        boolean[][] emptyGrid = new boolean[width][height];
        input.forEach(cell -> emptyGrid[cell.x()][cell.y()] = true);
        return emptyGrid;
    }
}
