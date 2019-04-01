import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class Block.
 *
 * @author Guy Shoham
 */
public class Block implements Collidable, Sprite {

    private Rectangle rect;
    private Color color;
    private int numberOfHits;

    /**
     * Class constructor.
     *
     * @param rectangle    the rectangle of the block.
     * @param color        the color of the block.
     * @param numberOfHits the number of hits before block disappears.
     */
    public Block(Rectangle rectangle, Color color, int numberOfHits) {
        this.rect = rectangle;
        this.color = color;
        this.numberOfHits = numberOfHits;
    }


    /**
     * Class constructor.
     *
     * @param rectangle the rectangle of the block.
     * @param color     the color of the block.
     */
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

    /**
     * draw the block on the drawSurface.
     *
     * @param surface surface.
     */
    @Override

    public void drawOn(DrawSurface surface) {
        rect.setColor(color);
        rect.drawOn(surface);

        String text = "x";
        if (numberOfHits != 0) {
            text = String.valueOf(numberOfHits);
        }
        surface.setColor(Color.WHITE);
        surface.drawText((int) (rect.getUpperLeft().getX() + (rect.getWidth() / 2)),
                (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 10 * 7)), text, 15);


    }

    /**
     * notify the block that time has passed.
     * currently do nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add the block to the game.
     *
     * @param g game
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public String toString() {
        return "Block{" + "rect=" + rect + "}\n";
    }
}
