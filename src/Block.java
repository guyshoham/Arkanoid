import java.awt.*;

public class Block implements Collidable {

    private Rectangle rectangle;
    private Color color;
    private int numberOfHits;

    public Block() {
        //todo: implement method
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //todo: implement method
        return null;
    }
}
