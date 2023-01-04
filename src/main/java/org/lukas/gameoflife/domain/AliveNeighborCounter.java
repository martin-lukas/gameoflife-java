package org.lukas.gameoflife.domain;

import java.util.Arrays;

/**
 * Calculates how many neighbors of a cell are alive.
 */
public class AliveNeighborCounter {
    /**
     * Counts the alive neighbors while respecting the cell grid's boundaries
     * (for example a corner cell only has 3 neighbors).
     * The grid should be rectangular, i.e. every sub-array should be of the same length.
     *
     * @param cells cells forming the grid
     * @param cellX X coordinate of the cell
     * @param cellY Y coordinate of the cell
     * @return number of alive neighbors of that cell
     */
    public int count(boolean[][] cells, int cellX, int cellY) {
        if (cells.length == 0 || cells[0].length == 0) {
            throw new IllegalArgumentException("The grid has to be at least 1x1 big.");
        }
        if (Arrays.stream(cells).anyMatch(col -> col.length != cells[0].length)) {
            throw new IllegalArgumentException("The grid must be rectangular.");
        }

        int aliveNeighborCount = 0;

        int neighborMinX = Math.max(cellX - 1, 0);
        int neighborMaxX = cellX + 1 >= cells.length
                ? cells.length - 1
                : cellX + 1;
        int neighborMinY = Math.max(cellY - 1, 0);
        int neighborMaxY = cellY + 1 >= cells[0].length
                ? cells[0].length - 1
                : cellY + 1;

        for (int x = neighborMinX; x <= neighborMaxX; x++) {
            for (int y = neighborMinY; y <= neighborMaxY; y++) {
                boolean isCellTarget = x == cellX && y == cellY;
                boolean isNeighborAlive = cells[x][y];
                if (!isCellTarget && isNeighborAlive) aliveNeighborCount++;
            }
        }

        return aliveNeighborCount;
    }
}
