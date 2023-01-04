package org.lukas.gameoflife.ui;

import org.lukas.gameoflife.domain.Game;

import javax.swing.*;

public class GameUI implements Runnable {
    private final int width;
    private final int height;
    private final String filePath;
    private final int intervalMs;

    public GameUI(int width, int height, String filePath, int intervalMs) {
        this.width = width;
        this.height = height;
        this.filePath = filePath;
        this.intervalMs = intervalMs;
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
        GamePane gamePane = new GamePane(game);

        JFrame frame = new JFrame("Game of Life");
        frame.add(gamePane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        Thread gameThread = new Thread(new GameRunner(game, gamePane, intervalMs));
        gameThread.setDaemon(true);
        gameThread.start();
    }
}
