
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Ball Model Object.
 *
 * @author Guy Shoham
 */
public class Ball implements Sprite {

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
        this.environment = new GameEnvironment();
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
     * @param surface the surface which the ball is drawn in.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }

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
            System.out.println("collisionInfo == null");
            //todo: maybe all this condition will be removed in the future (because ball cannot hit the walls)
            //check if the ball meet the right wall
            if (center.getX() + velocity.getDx() > bottomRightCorner.getX() && velocity.getDx() > 0) {
                //change ball from moving right to move left
                setVelocity(velocity.getDx() * -1, velocity.getDy());
            }
            //check if the ball meet the bottom wall
            if (center.getY() + velocity.getDy() > bottomRightCorner.getY() && velocity.getDy() > 0) {
                //change ball from moving down to move up
                setVelocity(velocity.getDx(), velocity.getDy() * -1);
            }
            //check if the ball meet the left wall
            if (center.getX() + velocity.getDx() < topLeftCorner.getX() && velocity.getDx() < 0) {
                //change ball from moving left to move right
                setVelocity(velocity.getDx() * -1, velocity.getDy());
            }
            //check if the ball meet the top wall
            if (center.getY() + velocity.getDy() < topLeftCorner.getY() && velocity.getDy() < 0) {
                //change ball from moving up to move down
                setVelocity(velocity.getDx(), velocity.getDy() * -1);
            }
            //modify the location of the ball
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
                newVelocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            } else if (nextPos.getX() + velocity.getDx() <= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() >= collisionPoint.getY()
                    && velocity.getDx() <= 0 && velocity.getDy() >= 0) {
                //the ball is coming from top right
                newVelocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            } else if (nextPos.getX() + velocity.getDx() >= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() <= collisionPoint.getY()
                    && velocity.getDx() >= 0 && velocity.getDy() <= 0) {
                //the ball is coming from bottom left
                newVelocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity);
                setVelocity(newVelocity);
            } else if (nextPos.getX() + velocity.getDx() <= collisionPoint.getX()
                    && nextPos.getY() + velocity.getDy() <= collisionPoint.getY()
                    && velocity.getDx() <= 0 && velocity.getDy() <= 0) {
                //the ball is coming from bottom right
                newVelocity = collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), velocity);
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

}