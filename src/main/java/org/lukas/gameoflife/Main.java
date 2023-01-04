package org.lukas.gameoflife;

import org.lukas.gameoflife.ui.GameUI;

import javax.swing.*;

public class Main {
    public static void main(String... args) {
        SwingUtilities.invokeLater(new GameUI(
                60,
                40,
                "src/main/resources/gun",
                20
        ));
    }
}
