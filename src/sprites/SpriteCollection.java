package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * class SpriteCollection.
 *
 * @author Guy Shoham
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Class constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * add the sprite object to the sprite collection.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * remove the sprite object from the sprite collection.
     *
     * @param s sprite.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<>(this.spriteList);
        // Notify all sprites about time passed:
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(surface) on all sprites.
     *
     * @param surface surface.
     */
    public void drawAllOn(DrawSurface surface) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(surface);
        }
    }
}