package gameobjects;

import backend.Counter;
import biuoop.DrawSurface;
import backend.Game;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * class ScoreIndicator.
 *
 * @author Guy Shoham
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter score;

    /**
     * class constructor.
     *
     * @param rectangle rectangle.
     * @param score     counter.
     */
    public ScoreIndicator(Rectangle rectangle, Counter score) {
        this.rect = rectangle;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        rect.setColor(Color.LIGHT_GRAY);
        rect.drawOn(surface);

        String text = String.valueOf(score.getValue());
        surface.setColor(Color.BLACK);
        surface.drawText(350, (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 10 * 7)), "Score: " + text, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the scoreIndicator to the game.
     *
     * @param g game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * remove the scoreIndicator from the game.
     *
     * @param g game
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

}
