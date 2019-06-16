package gameobjects;

import backend.GameLevel;
import biuoop.DrawSurface;
import backend.Collidable;
import drawer.Drawer;
import drawer.FillDrawer;
import drawer.StrokeDrawer;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import backend.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import backend.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * class Block.
 *
 * @author Guy Shoham
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rect;
    private int numberOfHits;
    private Drawer defaultFillDrawer, defaultStrokeDrawer;
    private HashMap<Integer, Drawer> fillDrawers, strokeDrawers;


    /**
     * Class constructor.
     *
     * @param rectangle    the rectangle of the block.
     * @param color        the color of the block.
     * @param numberOfHits the number of hits before block disappears.
     */
    public Block(Rectangle rectangle, Color color, int numberOfHits) {
        this.rect = rectangle;
        this.numberOfHits = numberOfHits;
        this.hitListeners = new ArrayList<>();

        this.defaultFillDrawer = new FillDrawer(color);
        this.defaultStrokeDrawer = new StrokeDrawer(color);
        this.strokeDrawers = new HashMap<>();
        this.fillDrawers = new HashMap<>();
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

    /**
     * Class constructor.
     *
     * @param x x position
     * @param y y position
     */
    public Block(int x, int y) {
        this.rect = new Rectangle(new Point(x, y), 1, 1);
        this.numberOfHits = 0;
        this.hitListeners = new ArrayList<>();

        this.defaultFillDrawer = null;
        this.defaultStrokeDrawer = null;
        this.strokeDrawers = new HashMap<>();
        this.fillDrawers = new HashMap<>();
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
        if (fillDrawers.containsKey(numberOfHits)) {
            fillDrawers.get(numberOfHits).draw(surface, rect);
        } else {
            defaultFillDrawer.draw(surface, rect);
        }
        if (!strokeDrawers.isEmpty() || defaultStrokeDrawer != null) {
            if (strokeDrawers.containsKey(numberOfHits)) {
                strokeDrawers.get(numberOfHits).draw(surface, rect);
            } else {
                defaultStrokeDrawer.draw(surface, rect);
            }
        }
    }

    /**
     * notify the block that time has passed.
     * currently do nothing.
     */
    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
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
     * @return number of hits.
     */
    public int getNumberOfHits() {
        return numberOfHits;
    }

    /**
     * @param hits numberOfHits.
     */
    public void setNumberOfHits(int hits) {
        this.numberOfHits = hits;
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

    /**
     * @param width width
     */
    public void setWidth(int width) {
        this.rect = new Rectangle(rect.getUpperLeft(), width, rect.getHeight());
    }

    /**
     * @param height height
     */
    public void setHeight(int height) {
        this.rect = new Rectangle(rect.getUpperLeft(), rect.getWidth(), height);
    }

    /**
     * @param d drawer
     */
    public void setDefaultStrokeDrawer(Drawer d) {
        defaultStrokeDrawer = d;
    }

    /**
     * @param d drawer
     */
    public void setDefaultFillDrawer(Drawer d) {
        defaultFillDrawer = d;
    }

    /**
     * @param hitPoints hit points
     * @param d         drawer
     */
    public void addStrokeDrawer(int hitPoints, Drawer d) {
        strokeDrawers.put(hitPoints, d);
    }

    /**
     * @param hitPoints hit points
     * @param d         drawer
     */
    public void addFillDrawer(int hitPoints, Drawer d) {
        fillDrawers.put(hitPoints, d);
    }
}
