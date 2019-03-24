
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Ball Model Object.
 *
 * @author Guy Shoham
 */
public class Ball {

    private Color color; //the color of the ball
    private Point center; //the location of the ball
    private int size; //the size of the ball (radius)
    private Velocity velocity; //the velocity of the ball
    private Point topLeftCorner, bottomRightCorner; //the position of the corners of the frame of the ball

    /**
     * Class constructor.
     *
     * @param center the location of the ball.
     * @param r      the size of the ball (radius).
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Class constructor.
     *
     * @param x     the x position of the ball.
     * @param y     the y position of the ball.
     * @param r     the size of the ball (radius).
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * @return the x position of the ball.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y position of the ball.
     */
    public int getY() {
        return (int) center.getY();

    }

    /**
     * @return the size of the ball.
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * this draw the ball on the given DrawSurface.
     *
     * @param surface the surface which the ball is drawn in.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
    }

    /**
     * set the velocity of the ball.
     *
     * @param v the velocity of the ball(x and y).
     */
    public void setVelocity(Velocity v) {
        velocity.setDx(v.getDx());
        velocity.setDy(v.getDy());
    }

    /**
     * set the velocity of the ball.
     *
     * @param dx the x velocity of the ball.
     * @param dy the y velocity of the ball.
     */
    public void setVelocity(double dx, double dy) {
        velocity.setDx(dx);
        velocity.setDy(dy);
    }

    /**
     * set the topLeftCorner of the ball.
     *
     * @param topLeft the top left position of the frame which the ball is in.
     */
    public void setTopLeftCorner(Point topLeft) {
        this.topLeftCorner = topLeft;
    }

    /**
     * set the bottomRightCorner of the ball.
     *
     * @param bottomRight the bottom right position of the frame which the ball is in.
     */
    public void setBottomRightCorner(Point bottomRight) {
        this.bottomRightCorner = bottomRight;
    }

    /**
     * @return the velocity of he ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * this method change the direction of the ball if the ball is about to hit one of the walls.
     */
    public void moveOneStep() {
        //check if the ball meet the right wall
        if (center.getX() + velocity.getDx() + size > bottomRightCorner.getX() && velocity.getDx() > 0) {
            //change ball from moving right to move left
            setVelocity(velocity.getDx() * -1, velocity.getDy());
        }
        //check if the ball meet the bottom wall
        if (center.getY() + velocity.getDy() + size > bottomRightCorner.getY() && velocity.getDy() > 0) {
            //change ball from moving down to move up
            setVelocity(velocity.getDx(), velocity.getDy() * -1);
        }
        //check if the ball meet the left wall
        if (center.getX() + velocity.getDx() - size < topLeftCorner.getX() && velocity.getDx() < 0) {
            //change ball from moving left to move right
            setVelocity(velocity.getDx() * -1, velocity.getDy());
        }
        //check if the ball meet the top wall
        if (center.getY() + velocity.getDy() - size < topLeftCorner.getY() && velocity.getDy() < 0) {
            //change ball from moving up to move down
            setVelocity(velocity.getDx(), velocity.getDy() * -1);
        }
        //modify the location of the ball
        center = this.getVelocity().applyToPoint(center);
    }


}