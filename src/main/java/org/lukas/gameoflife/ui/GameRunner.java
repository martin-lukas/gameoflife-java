package org.lukas.gameoflife.ui;

import org.lukas.gameoflife.domain.Game;

public class GameRunner implements Runnable {
    private final Game game;
    private final GamePane gamePane;
    private final int intervalMs;

    public GameRunner(Game game, GamePane gamePane, int intervalMs) {
        this.game = game;
        this.gamePane = gamePane;
        this.intervalMs = intervalMs;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            regularPause();
            game.calculateNextState();
            gamePane.repaint();
        }
    }

    private void regularPause() {
        try {
            Thread.sleep(intervalMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
