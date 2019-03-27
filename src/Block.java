import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable {

    private Rectangle rectangle;
    private Color color;
    private int numberOfHits;

    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.numberOfHits = 0; //todo: need to be repaired
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        numberOfHits--;
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

    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }
}
