package org.lukas.gameoflife.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void testGameProgress_3x1Shape() {
        var input = List.of(
                Cell.of(0, 1),
                Cell.of(1, 1),
                Cell.of(2, 1)
        );
        Game game = new Game(3, 3, input);

        var expectedInitialGrid = new boolean[][] {
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };

        IntStream.rangeClosed(0, 2).forEach(i ->
                assertArrayEquals(expectedInitialGrid[i], game.getCurrentGrid()[i])
        );

        game.calculateNextState();

        var expectedNextGrid = new boolean[][] {
                {false, false, false},
                {true, true, true},
                {false, false, false}
        };

        IntStream.rangeClosed(0, 2).forEach(i ->
                assertArrayEquals(expectedNextGrid[i], game.getCurrentGrid()[i])
        );
    }
}
