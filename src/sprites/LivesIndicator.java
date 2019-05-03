package sprites;

import Listeners.Counter;
import biuoop.DrawSurface;
import game.Game;
import geometry.Rectangle;

import java.awt.*;

public class LivesIndicator implements Sprite {
    private Rectangle rect;
    private Counter lives;

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
