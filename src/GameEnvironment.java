import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables;

    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {

        List<CollisionInfo> collisionInfos = new ArrayList<>();

        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point collidePoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (collidePoint != null) {
                //collision
                collisionInfos.add(new CollisionInfo(collidePoint, collidable));
            }
        }

        if (collisionInfos.isEmpty()) {
            System.out.print(collidables.get(2));
            System.out.println("trajectory = " + trajectory);
            return null;
        }
        if (collisionInfos.size() == 1) {
            return collisionInfos.get(0);
        }

        CollisionInfo closest = collisionInfos.get(0);
        double minDistance = collisionInfos.get(0).collisionPoint().distance(trajectory.getStart());

        for (CollisionInfo collisionInfo : collisionInfos) {
            double newDistance = collisionInfo.collisionPoint().distance(trajectory.getStart());
            if (collisionInfo.collisionPoint().distance(trajectory.getStart()) < minDistance) {
                closest = collisionInfo;
                minDistance = newDistance;
            }
        }
        return closest;
    }
}