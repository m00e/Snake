package de.m00e.snake.frameutils;

import de.m00e.snake.gameutils.Game;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private static Game game;
    private static final int SIZE = 800;

    public Frame() {
        super("Snake");
        this.pack();
        this.setSize(SIZE, SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        game = new Game();

        this.add(new GameSimulation());
        this.addKeyListener(new MovementListener());
    }

    /**
     * Simulating the "Snake" game in a JLabel.
     */
    private class GameSimulation extends JLabel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            game.draw(g);
        }
    }

    public void updateState() {
        game.update();
    }

    // Getter & Setter

    public Game getGame() {
        return game;
    }
    public static int getFrameSize() {
        return SIZE;
    }
}
