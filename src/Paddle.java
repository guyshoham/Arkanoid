import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import static biuoop.KeyboardSensor.LEFT_KEY;
import static biuoop.KeyboardSensor.RIGHT_KEY;

public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private static final int SPEED = 5;
    private static final double LEFT_CORNER = 25;
    private static final double RIGHT_CORNER = 775;

    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard) {
        this.rect = rectangle;
        this.color = color;
        this.keyboard = keyboard;
    }

    public void moveLeft() {
        Point newUpperLeft = new Point(rect.getUpperLeft().getX() - SPEED, rect.getUpperLeft().getY());
        //check if the rectangle new position is in borders
        if (newUpperLeft.getX() >= LEFT_CORNER) {
            //move the X pos of rect to the left
            rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
        }
    }

    public void moveRight() {
        Point newUpperLeft = new Point(rect.getUpperLeft().getX() + SPEED, rect.getUpperLeft().getY());
        //check if the rectangle new position is in borders
        if (newUpperLeft.getX() + rect.getWidth() <= RIGHT_CORNER) {
            //move the X pos of rect to the right
            rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
        }
    }

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(RIGHT_KEY)) {
            moveRight();
        }
    }

    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity retVal = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        double hitX = collisionPoint.getX();

        double region1 = rect.getUpperLeft().getX() + (rect.getWidth() / 5 * 0);
        double region2 = rect.getUpperLeft().getX() + (rect.getWidth() / 5 * 1);
        double region3 = rect.getUpperLeft().getX() + (rect.getWidth() / 5 * 2);
        double region4 = rect.getUpperLeft().getX() + (rect.getWidth() / 5 * 3);
        double region5 = rect.getUpperLeft().getX() + (rect.getWidth() / 5 * 4);
        double region6 = rect.getUpperLeft().getX() + (rect.getWidth() / 5 * 5);

        //region 1
        if (hitX >= region1 && hitX < region2) {
            retVal.setDy(currentVelocity.getDy() * -1);
            retVal.setDx(currentVelocity.getDy() * -1.3);
        }
        //region 2
        if (hitX >= region2 && hitX < region3) {
            retVal.setDy(currentVelocity.getDy() * -1);
            retVal.setDx(currentVelocity.getDy() * -1.15);
        }
        //region 3
        if (hitX >= region3 && hitX < region4) {
            retVal.setDy(currentVelocity.getDy() * -1);
        }
        //region 4
        if (hitX >= region4 && hitX < region5) {
            retVal.setDy(currentVelocity.getDy() * -1);
            retVal.setDx(currentVelocity.getDy() * 1.15);
        }
        //region 5
        if (hitX >= region5 && hitX < region6) {
            retVal.setDx(currentVelocity.getDy() * 1.3);
            retVal.setDy(currentVelocity.getDy() * -1);
        }

        return retVal;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}