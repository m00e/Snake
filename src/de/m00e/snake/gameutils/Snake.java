package de.mooooooe.snake.gameutils;

import de.mooooooe.snake.gameutils.enums.Movement;
import de.mooooooe.snake.gameutils.enums.State;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class that contains the snake and the internal class "Tail".
 */
public class Snake {

    private static Movement movement;
    private static State state;
    private int headX, headY;
    private static final int CYCLE_SIZE = Cell.getSize();

    private final ArrayList<Tail> tails;

    public Snake() {
        headX = Game.getCellAmount()/2 * Cell.getSize();
        headY = headX; // Game field is a square, not necessary to calculate center 2x.
        movement = Movement.RIGHT;
        state = State.ALIVE;

        tails = new ArrayList<Tail>();
    }

    /**
     * Adds a new tail piece to the tail line.
     */
    public void addNewTail() {
        Point point = calcCoords();
        tails.add(new Tail(point.x, point.y));
    }

    /**
     * Get last 2 tail pieces and calculate, based on the arrangement of those two pieces, the new coordinates.
     * @return Point(x,y) of the new tail piece.
     */
    public Point calcCoords() {
        Point point = new Point();
        int tailLength = tails.size();

        if(tailLength <= 1) {
            // Calculate coordinates depending on head (+ tail).
            switch(movement) {
                case UP:
                    point = new Point(headX, headY+(tailLength+1)*CYCLE_SIZE);
                    break;
                case DOWN:
                    point = new Point(headX, headY-(tailLength+1)*CYCLE_SIZE);
                    break;
                case LEFT:
                    point = new Point(headX+(tailLength+1)*CYCLE_SIZE, headY);
                    break;
                case RIGHT:
                    point = new Point(headX-(tailLength+1)*CYCLE_SIZE, headY);
                    break;
            }
        } else {
            // Calculate coordinates depending on the last 2 tail pieces.
            Tail secLastTail = tails.get(tails.size()-2);
            Tail lastTail = tails.get(tails.size()-1);

            int x1 = secLastTail.x, y1 = secLastTail.y;
            int x2 = lastTail.x, y2 = lastTail.y;

            if(y1 == y2 && x1 > x2) {
                point = new Point(lastTail.x-CYCLE_SIZE, lastTail.y);
            } else if(y1 == y2 && x1 < x2) {
                point = new Point(lastTail.x+CYCLE_SIZE, lastTail.y);
            } else if(x1 == x2 && y1 < y2) {
                point = new Point(lastTail.x, lastTail.y+CYCLE_SIZE);
            } else {
                point = new Point(lastTail.x, lastTail.y-CYCLE_SIZE);
            }
        }

        return point;
    }

    public class Tail {

        private int x;
        private int y;

        public Tail(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        /**
         * Draw tail piece.
         * @param g Obligatory Graphics instance.
         */
        public void draw(Graphics g) {
            g.setColor(Color.green);
            g.fillOval(x, y, CYCLE_SIZE, CYCLE_SIZE);
        }
    }

    /**
     * Draw head piece.
     * @param g Obligatory Graphics instance.
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(headX, headY, CYCLE_SIZE, CYCLE_SIZE);

        for(Tail tail: tails) {
            tail.draw(g);
        }
    }

    public void move() {
        int preX = headX;
        int preY = headY;

        switch(movement) {
            case UP:
                headY -= Cell.getSize();
                break;
            case DOWN:
                headY += Cell.getSize();
                break;
            case RIGHT:
                headX += Cell.getSize();
                break;
            case LEFT:
                headX -= Cell.getSize();
                break;
        }

        int ownX;
        int ownY;

        // Move tail pieces to the points where the predecessor was in the previous frame.
        for(Tail tail: tails) {
            ownX = tail.x;
            ownY = tail.y;

            tail.x = preX;
            tail.y = preY;

            preX = ownX;
            preY = ownY;
        }
    }

    /**
     * Checks if snake is still alive.
     * @return s.o.
     */
    public boolean isAlive() {
        return state == State.ALIVE;
    }

    /**
     * Function is called when the snake collides with a wall = Game over.
     */
    public void setDead() {
        state = State.DEAD;
    }

    // Getter & Setter
    public void setMovement(Movement newMovement) {
        movement = newMovement;
    }

    public int getHeadX() {
        return headX;
    }

    public int getHeadY() {
        return headY;
    }

    public ArrayList<Tail> getTails() {
        return tails;
    }
}
