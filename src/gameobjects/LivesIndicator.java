package gameobjects;

import backend.Counter;
import biuoop.DrawSurface;
import backend.Game;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * class LivesIndicator.
 *
 * @author Guy Shoham
 */
public class LivesIndicator implements Sprite {
    private Rectangle rect;
    private Counter lives;

    /**
     * class constructor.
     *
     * @param rectangle rectangle.
     * @param lives     counter.
     */
    public LivesIndicator(Rectangle rectangle, Counter lives) {
        this.rect = rectangle;
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        rect.setColor(Color.LIGHT_GRAY);
        rect.drawOn(surface);

        String text = String.valueOf(lives.getValue());
        surface.setColor(Color.BLACK);
        surface.drawText(25, (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 10 * 7)), "Lives: " + text, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the livesIndicator to the game.
     *
     * @param g game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * remove the livesIndicator from the game.
     *
     * @param g game
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}