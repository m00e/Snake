package de.mooooooe.snake.gameutils;

import java.awt.*;
import java.util.Random;

public class Food {

    private int x, y;
    private static Random random = new Random();
    private static final int SIZE = Cell.getSize();

    // TODO: Spawn outside of Head and tail
    // TODO: Bug: Food doesn't spawn sometimes... maybe out of bounds?
    public Food() {
        x = random.nextInt(Game.getCellAmount()-1);
        y = random.nextInt(Game.getCellAmount()-1);

        if(x == 0) x++;
        if(y == 0) y++;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x*SIZE, y*SIZE, SIZE, SIZE);
    }

    public int getX() {
        return x*SIZE;
    }

    public int getY() {
        return y*SIZE;
    }
}
