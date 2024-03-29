package indicators;

import backend.Counter;
import backend.GameLevel;
import backend.Sprite;
import biuoop.DrawSurface;
import geometry.Rectangle;

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
        surface.drawText(270, (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 10 * 7)), "Score: " + text, 15);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}
