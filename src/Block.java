import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {

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
        //numberOfHits--;
        Velocity retVal = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //check on which edge is the point colliding
        if (Line.isPointInLine(rectangle.getTopEdge(), collisionPoint)) {
            retVal.setDy(currentVelocity.getDy() * -1);
        }
        if (Line.isPointInLine(rectangle.getBottomEdge(), collisionPoint)) {
            retVal.setDy(currentVelocity.getDy() * -1);
        }
        if (Line.isPointInLine(rectangle.getLeftEdge(), collisionPoint)) {
            retVal.setDx(currentVelocity.getDx() * -1);
        }
        if (Line.isPointInLine(rectangle.getRightEdge(), collisionPoint)) {
            retVal.setDx(currentVelocity.getDx() * -1);
        }
        return retVal;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
