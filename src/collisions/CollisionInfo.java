package collisions;

import geometry.Point;

/**
 * class CollisionInfo.
 *
 * @author Guy Shoham
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Class constructor.
     *
     * @param point  the collision point.
     * @param object the collision object.
     */
    public CollisionInfo(Point point, Collidable object) {
        this.collisionPoint = point;
        this.collisionObject = object;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    @Override
    public String toString() {
        return "CollisionInfo{" + "collisionPoint=" + collisionPoint + ", collisionObject=" + collisionObject + '}';
    }
}