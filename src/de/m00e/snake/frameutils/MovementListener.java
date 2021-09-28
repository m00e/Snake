package de.m00e.snake.frameutils;

import de.m00e.snake.Main;
import de.m00e.snake.gameutils.enums.Movement;
import de.m00e.snake.gameutils.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Move keys.
 */
public class MovementListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        Snake snake = Main.getFrame().getGame().getSnake();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.setMovement(Movement.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setMovement(Movement.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setMovement(Movement.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                snake.setMovement(Movement.LEFT);
                break;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
