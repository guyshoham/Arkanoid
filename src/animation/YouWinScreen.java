package animation;

import biuoop.DrawSurface;

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
        d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}