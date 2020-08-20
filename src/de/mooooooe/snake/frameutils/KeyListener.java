package de.mooooooe.snake.frameutils;

import de.mooooooe.snake.Main;
import de.mooooooe.snake.gameutils.enums.Movement;
import de.mooooooe.snake.gameutils.Snake;

import java.awt.event.KeyEvent;

/**
 * Move keys.
 */
public class KeyListener implements java.awt.event.KeyListener {

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
