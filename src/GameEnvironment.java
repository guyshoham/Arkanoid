import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
        //todo: implement method
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        //todo: implement method
        return null;
    }

}