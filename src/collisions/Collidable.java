package collisions;

import gameobjects.Ball;
import geometry.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * interface Collidable.
 *
 * @author Guy Shoham
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter the ball.
     * @param collisionPoint  the collision point of the collidable object and the ball.
     * @param currentVelocity current velocity of the hitting ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}