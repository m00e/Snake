package de.m00e.snake;

import de.m00e.snake.frameutils.Frame;

import javax.swing.*;

public class Main {

    private static Frame frame;

    public static void main(String[] args) {
        do { // Play again if YES-Option is selected.
            frame = new Frame();
            frame.setVisible(true);

            while (frame.getGame().getSnake().isAlive()) {
                frame.updateState();
                frame.repaint();
            }
        } while(JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        System.exit(0);
    }

    public static Frame getFrame() {
        return frame;
    }
}
