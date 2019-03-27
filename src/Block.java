import java.awt.Color;

public class Block implements Collidable {

    private Rectangle rectangle;
    private Color color;
    private int numberOfHits;

    public Block() {
        //todo: implement constructor?
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //check on which edge is the point colliding
        if (Line.isPointInLine(rectangle.getTopEdge(), collisionPoint)) {
            currentVelocity.setDy(currentVelocity.getDy() * -1);
        }
        if (Line.isPointInLine(rectangle.getBottomEdge(), collisionPoint)) {
            currentVelocity.setDy(currentVelocity.getDy() * -1);
        }
        if (Line.isPointInLine(rectangle.getLeftEdge(), collisionPoint)) {
            currentVelocity.setDx(currentVelocity.getDx() * -1);
        }
        if (Line.isPointInLine(rectangle.getRightEdge(), collisionPoint)) {
            currentVelocity.setDx(currentVelocity.getDx() * -1);
        }

        return currentVelocity;
    }
}
