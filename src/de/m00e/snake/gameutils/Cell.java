package de.m00e.snake.gameutils;

import java.awt.*;

public class Cell {

    private int x, y;
    private static final int SIZE = 20;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, SIZE, SIZE);
    }

    public static int getSize() {
        return SIZE;
    }
}
