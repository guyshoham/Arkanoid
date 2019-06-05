package animation;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * class YouWinScreen.
 *
 * @author Guy Shoham
 */
public class YouWinScreen implements Animation {
    private int score;

    /**
     * Class Constructor.
     *
     * @param score score
     */
    public YouWinScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawText(300, 200, "You Win!", 50);
        d.setColor(Color.BLACK);
        d.drawText(250, 300, "Your Score is " + score, 40);

        d.drawText(250, 500, "Press Space to Continue", 30);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}