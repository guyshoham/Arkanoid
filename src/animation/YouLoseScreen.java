package animation;

import biuoop.DrawSurface;

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
        d.drawText(10, d.getHeight() / 2, "Game Over. Your Score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}