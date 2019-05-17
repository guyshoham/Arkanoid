package gameobjects;

import backend.GameLevel;
import biuoop.DrawSurface;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.*;

/**
 * class LivesIndicator.
 *
 * @author Guy Shoham
 */
public class LevelNameIndicator implements Sprite {
    private Rectangle rect;
    private String name;

    /**
     * class constructor.
     *
     * @param rectangle rectangle.
     * @param name      level name.
     */
    public LevelNameIndicator(Rectangle rectangle, String name) {
        this.rect = rectangle;
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        rect.setColor(Color.LIGHT_GRAY);
        rect.drawOn(surface);

        surface.setColor(Color.BLACK);
        surface.drawText(550, (int) (rect.getUpperLeft().getY() + (rect.getHeight() / 10 * 7)), "Level Name: " + name, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the livesIndicator to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the livesIndicator from the game.
     *
     * @param g game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
