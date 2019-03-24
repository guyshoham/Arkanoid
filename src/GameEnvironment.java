import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {

        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point collidePoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (collidePoint != null) {
                return new CollisionInfo(collidePoint, collidable);
            }
        }
        return null;
    }
}