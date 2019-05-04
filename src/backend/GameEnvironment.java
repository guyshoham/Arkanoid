package backend;

import collisions.Collidable;
import collisions.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * class GameEnvironment.
 *
 * @author Guy Shoham
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Class constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove the collidable object from the game environment.
     *
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur
     *
     * @param trajectory trajectory line.
     * @return the closest collision, as CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        List<CollisionInfo> collisionInfos = new ArrayList<>();

        //checks for each collidable if the trajectory has intersection point with the object
        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point collidePoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (collidePoint != null) {
                //there is a collision
                collisionInfos.add(new CollisionInfo(collidePoint, collidable));
            }
        }

        //if list is empty, return null
        if (collisionInfos.isEmpty()) {
            return null;
        }
        //if list has one object, return the only object
        if (collisionInfos.size() == 1) {
            return collisionInfos.get(0);
        }

        CollisionInfo closestCollision = collisionInfos.get(0);
        double minDistance = collisionInfos.get(0).collisionPoint().distance(trajectory.start());

        //check for the closest collision from list
        for (CollisionInfo collisionInfo : collisionInfos) {
            //compute the distance, and compare with min distance so far
            double newDistance = collisionInfo.collisionPoint().distance(trajectory.start());
            if (collisionInfo.collisionPoint().distance(trajectory.start()) < minDistance) {
                closestCollision = collisionInfo;
                minDistance = newDistance;
            }
        }
        return closestCollision;
    }
}