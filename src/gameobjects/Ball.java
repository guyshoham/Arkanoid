package gameobjects;

import backend.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import biuoop.DrawSurface;
import collisions.CollisionInfo;
import backend.GameEnvironment;
import geometry.Line;
import geometry.Point;
import backend.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Ball Model Object.
 *
 * @author Guy Shoham
 */
public class Ball implements Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Color color; //the color of the ball
    private Point center; //the location of the ball
    private int size; //the size of the ball (radius)
    private Velocity velocity; //the velocity of the ball
    private Point topLeftCorner, bottomRightCorner; //the position of the corners of the frame of the ball
    private static GameEnvironment environment;

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
        environment = new GameEnvironment();
        this.hitListeners = new ArrayList<>();
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
    public Color getColor() {
        return color;
    }

    /**
     * this draw the ball on the given DrawSurface.
     *
     * @param surface surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), size);
    }

    /**
     * notify the ball that time has passed. called moveOneStep method.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * adds the ball to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this method sets the gameEnvironment of the ball.
     *
     * @param env the gameEnvironment.
     */
    public static void setEnvironment(GameEnvironment env) {
        Ball.environment = env;
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
     * this method change the direction of the ball if the ball is about to hit a collidable object.
     */
    public void moveOneStep() {
        //compute trajectory
        Line trajectory = computeTrajectory();
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);

        if (collisionInfo == null) {
            center = this.getVelocity().applyToPoint(center);
        } else {
            //there is a collision with object. check if next step is the collision point.
            Point collisionPoint = collisionInfo.collisionPoint();
            Point nextPos = new Point(center.getX() + velocity.getDx(), center.getY() + velocity.getDy());
            Velocity newVelocity = velocity;

            if (nextPos.getX() + velocity.getDx() >= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() >= collisionPoint.getY()
                    && velocity.getDx() >= 0 && velocity.getDy() >= 0) {
                //the ball is coming from top left
                newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            } else if (nextPos.getX() + velocity.getDx() <= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() >= collisionPoint.getY()
                    && velocity.getDx() <= 0 && velocity.getDy() >= 0) {
                //the ball is coming from top right
                newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            } else if (nextPos.getX() + velocity.getDx() >= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() <= collisionPoint.getY()
                    && velocity.getDx() >= 0 && velocity.getDy() <= 0) {
                //the ball is coming from bottom left
                newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            } else if (nextPos.getX() + velocity.getDx() <= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() <= collisionPoint.getY()
                    && velocity.getDx() <= 0 && velocity.getDy() <= 0) {
                //the ball is coming from bottom right
                newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            }

            if (velocity.equal(newVelocity)) {
                //velocity did not change after all, move ball
                center = this.getVelocity().applyToPoint(center);
            } else {
                setVelocity(newVelocity);
            }
        }
    }

    /**
     * this method compute trajectory of the ball.
     * the trajectory is "how the ball will move without any obstacles" -- its a line starting at current location,
     * and ending where the velocity will take the ball if no collisions will occur.
     *
     * @return the trajectory line
     */
    public Line computeTrajectory() {
        Point currentPos = center;
        Line retVal = new Line(center, currentPos);
        while (true) {
            currentPos = this.getVelocity().applyToPoint(currentPos);
            if (!currentPos.isInRect(topLeftCorner, bottomRightCorner)) {
                //hit!
                return retVal;
            } else {
                retVal = new Line(center, currentPos);
            }
        }
    }

    /**
     * remove the ball from the game.
     *
     * @param g game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}