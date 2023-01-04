package org.lukas.gameoflife.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AliveNeighborCounterTest {
    @Test
    void count_normalBehavior() {
        var counter = new AliveNeighborCounter();

        boolean[][] cells = new boolean[][]{
                {true, false},
                {false, false}
        };

        // Alive has no neighbors
        assertEquals(0, counter.count(cells, 0, 0));

        // Alive has one neighbor
        cells[0][1] = true;
        assertEquals(1, counter.count(cells, 0, 0));

        // Dead has one neighbor still
        cells[0][0] = false;
        assertEquals(1, counter.count(cells, 0, 0));

        // Grid is full
        cells[0][0] = true;
        cells[0][1] = true;
        cells[1][0] = true;
        cells[1][1] = true;
        assertEquals(3, counter.count(cells, 1, 1));
    }

    @Test
    void count_exceptionalBehavior() {
        var counter = new AliveNeighborCounter();
        assertThrows(IllegalArgumentException.class, () ->
            counter.count(new boolean[][]{}, 0, 0)
        );
        assertThrows(IllegalArgumentException.class, () ->
            counter.count(new boolean[][] {{false, false}, {false}}, 0, 0)
        );
    }
}
