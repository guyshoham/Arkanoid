import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {

    private Rectangle rect;
    private Color color;
    private int numberOfHits;

    public Block(Rectangle rectangle, Color color, int numberOfHits) {
        this.rect = rectangle;
        this.color = color;
        this.numberOfHits = numberOfHits;
    }


    public Block(Rectangle rectangle, Color color) {
        this(rectangle, color, 0);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (numberOfHits != 0) {
            numberOfHits--;
        }
        Velocity retVal = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //check on which edge is the point colliding
        if (Line.isPointInLine(rect.getTopEdge(), collisionPoint)) {
            retVal.setDy(currentVelocity.getDy() * -1);
        }
        if (Line.isPointInLine(rect.getBottomEdge(), collisionPoint)) {
            retVal.setDy(currentVelocity.getDy() * -1);
        }
        if (Line.isPointInLine(rect.getLeftEdge(), collisionPoint)) {
            retVal.setDx(currentVelocity.getDx() * -1);
        }
        if (Line.isPointInLine(rect.getRightEdge(), collisionPoint)) {
            retVal.setDx(currentVelocity.getDx() * -1);
        }
        return retVal;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

        String text = "x";
        if (numberOfHits != 0) {
            text = String.valueOf(numberOfHits);
        }
        surface.setColor(Color.WHITE);
        surface.drawText((int) (rect.getUpperLeft().getX() + (rect.getWidth() / 2)),
                (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 2)), text, 15);


    }

    @Override
    public void timePassed() {

    }

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public String toString() {
        return "Block{" + "rect=" + rect + "}\n";
    }
}
