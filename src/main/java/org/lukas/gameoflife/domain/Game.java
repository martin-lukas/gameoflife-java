package org.lukas.gameoflife.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Game {
    private final int width;
    private final int height;
    private boolean[][] cells;
    private final AliveNeighborCounter neighborCounter;

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
        this.neighborCounter = new AliveNeighborCounter();
    }

    /**
     * Constructor for the game class.
     * @param width width of the game grid (number of columns)
     * @param height height of the game grid (number of rows)
     * @param filePath path to a file with cells' coordinates
     */
    public Game(int width, int height, String filePath) {
        this.width = width;
        this.height = height;
        List<Cell> input = getCellsFromFile(filePath);
        validateInput(input);
        this.cells = populateCells(width, height, input);
        this.neighborCounter = new AliveNeighborCounter();
    }

    public boolean[][] getCurrentGrid() {
        return cells;
    }

    public void calculateNextState() {
        cells = getNextGridState();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean[][] getNextGridState() {
        boolean[][] nextGridState = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int aliveNeighbors = neighborCounter.count(cells, x, y);
                boolean isCellAlive = cells[x][y];
                boolean isCellNotOvercrowded = isCellAlive && aliveNeighbors >= 2 && aliveNeighbors <= 3;
                boolean shouldCellComeAlive = !isCellAlive && aliveNeighbors == 3;
                nextGridState[x][y] = isCellNotOvercrowded || shouldCellComeAlive;
            }
        }

        return nextGridState;
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

    private List<Cell> getCellsFromFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath))
                    .stream()
                    .map(String::trim)
                    .filter(Predicate.not(String::isBlank))
                    .filter(str -> !str.startsWith("//"))
                    .map(str -> {
                        String[] strCoordinates = str.split(" ");
                        return Cell.of(
                                Integer.parseInt(strCoordinates[0]),
                                Integer.parseInt(strCoordinates[1])
                        );
                    })
                    .toList();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
