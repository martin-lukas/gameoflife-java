package org.lukas.gameoflife.domain;

public record Cell(int x, int y) {
    public static Cell of(int x, int y) {
        return new Cell(x, y);
    }
}
