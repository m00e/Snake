package de.mooooooe.snake.gameutils;

import de.mooooooe.snake.frameutils.Frame;

import java.awt.*;

/**
 * Actual "Snake" game simulation.
 */
public class Game {

    private static Cell[][] cells;
    private Snake snake;
    private Food food;
    private static final int CELL_AMOUNT = Frame.getFrameSize()/ Cell.getSize();
    private final int FPS = 10;

    public Game() {
        initGame();
    }

    public void initGame() {
        cells = new Cell[CELL_AMOUNT][CELL_AMOUNT];
        snake = new Snake();
        food = new Food();

        for(int i = 0; i < CELL_AMOUNT; i++) {
            for(int j = 0; j < CELL_AMOUNT; j++) {
                cells[i][j] = new Cell(i * Cell.getSize(), j * Cell.getSize());
            }
        }
    }

    /**
     * Draw the field, snake head and the first food.
     * @param g Obligatory Graphics instance.
     */
    public void draw(Graphics g) {
        for(int i = 0; i < CELL_AMOUNT; i++) {
            for(int j = 0; j < CELL_AMOUNT; j++) {
                cells[i][j].draw(g);
            }
        }

        snake.draw(g);
        food.draw(g);
    }

    /**
     * Code that is executed every frame.
     */
    public void update() {
        snake.move();

        // Check if snake collides with wall
        if(isOutOfBounds()) {
            snake.setDead();
            return;
        }

        // Check if snake eats food; adds new tail to body.
        if(snake.getHeadX() == food.getX() && snake.getHeadY() == food.getY()) {
            food = new Food();
            snake.addNewTail();
        }

        // Check if snake eats own tail, if so, remove tail part.
        for(int i = 0; i < snake.getTails().size(); i++) {
            if(snake.getTails().get(i).getX() == snake.getHeadX() && snake.getTails().get(i).getY() == snake.getHeadY()) {
                snake.getTails().subList(i, snake.getTails().size()).clear();
            }
        }

        // Waiting until next update. (Depending on frames per second).
        try {
            Thread.sleep(1000/FPS);
        } catch(InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Checks if snake collides with a wall.
     * @return true = Gameover.
     */
    public boolean isOutOfBounds() {
        if(snake.getHeadX() >= CELL_AMOUNT*Cell.getSize() ||
                snake.getHeadY() >= CELL_AMOUNT*Cell.getSize() ||
                snake.getHeadX() < 0 ||
                snake.getHeadY() < 0) {
            return true;
        }
        return false;
    }

    // Getter & Setter
    public static int getCellAmount() {
        return CELL_AMOUNT;
    }

    public Snake getSnake() {
        return snake;
    }
}
