package sprites;

import Listeners.Counter;
import biuoop.DrawSurface;
import game.Game;
import geometry.Rectangle;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter score;

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
