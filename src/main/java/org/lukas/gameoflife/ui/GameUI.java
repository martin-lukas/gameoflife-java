package org.lukas.gameoflife.ui;

import org.lukas.gameoflife.domain.Game;

import javax.swing.*;

public class GameUI implements Runnable {
    private final int width;
    private final int height;
    private final String filePath;

    public GameUI(int width, int height, String filePath) {
        this.width = width;
        this.height = height;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException ex
        ) {
            throw new RuntimeException(ex);
        }

        Game game = new Game(width, height, filePath);

        JFrame frame = new JFrame("Game of Life");
        frame.add(new GamePane(game));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
