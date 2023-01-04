package org.lukas.gameoflife.ui;

import org.lukas.gameoflife.domain.Game;

import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel {
    private static final int CELL_SIZE = 20;

    private final Game game;

    public GamePane(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean[][] cells = game.getCurrentGrid();
        int width = game.getWidth();
        int height = game.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y]) {
                    g.fillRect(
                            CELL_SIZE * x,
                            CELL_SIZE * (height - y - 1),
                            CELL_SIZE,
                            CELL_SIZE
                    );
                } else {
                    g.drawRect(
                            CELL_SIZE * x,
                            CELL_SIZE * (height - y - 1),
                            CELL_SIZE,
                            CELL_SIZE
                    );
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                game.getWidth() * CELL_SIZE,
                game.getHeight() * CELL_SIZE
        );
    }
}
