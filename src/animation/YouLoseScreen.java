package animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class YouLoseScreen.
 *
 * @author Guy Shoham
 */
public class YouLoseScreen implements Animation {
    private int score;

    /**
     * Class Constructor.
     *
     * @param score score
     */
    public YouLoseScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText(285, 200, "Game Over!", 50);
        d.setColor(Color.BLACK);
        d.drawText(250, 300, "Your Score is " + score, 40);

        d.drawText(250, 500, "Press Space to Continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}