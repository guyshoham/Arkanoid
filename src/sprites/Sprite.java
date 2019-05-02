package sprites;

import biuoop.DrawSurface;

/**
 * interface Sprite.
 *
 * @author Guy Shoham
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param surface surface
     */
    void drawOn(DrawSurface surface);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}