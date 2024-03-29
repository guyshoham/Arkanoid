package backend;

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

    /**
     * add the sprite to the game.
     *
     * @param g game
     */
    void addToGame(GameLevel g);

    /**
     * remove the sprite from the game.
     *
     * @param g game
     */
    void removeFromGame(GameLevel g);
}