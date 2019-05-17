package gameobjects;

import backend.Velocity;
import biuoop.DrawSurface;
import collisions.Collidable;
import backend.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class Block.
 *
 * @author Guy Shoham
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
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
        this.hitListeners = new ArrayList<>();
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
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
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

        /*String text = "x";
        if (numberOfHits != 0) {
            text = String.valueOf(numberOfHits);
        }
        surface.setColor(Color.WHITE);
        surface.drawText((int) (rect.getUpperLeft().getX() + (rect.getWidth() / 2)),
                (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 10 * 7)), text, 15);
        */
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
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove the block from the game.
     *
     * @param g game
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     *
     * @param hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @return number of hits as string.
     */
    public String getHitPoints() {
        return String.valueOf(numberOfHits);
    }

    /**
     * @param hits numberOfHits.
     */
    public void setNumberOfHits(int hits) {
        this.numberOfHits = hits;
    }

    /**
     * @return number of hits.
     */
    public int getNumberOfHits() {
        return numberOfHits;
    }

    /**
     * remove 1 hit form number of hits.
     */
    public void removeHit() {
        numberOfHits--;
    }

    @Override
    public String toString() {
        return "Block{" + "rect=" + rect + "}\n";
    }
}
