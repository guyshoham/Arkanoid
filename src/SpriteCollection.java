import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * class SpriteCollection.
 *
 * @author Guy Shoham
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Class constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * add the sprite object to the sprite collection.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
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
        for (Sprite sprite : sprites) {
            sprite.drawOn(surface);
        }
    }
}