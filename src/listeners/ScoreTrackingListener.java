package listeners;

import backend.Counter;
import gameobjects.Ball;
import gameobjects.Block;

/**
 * class ScoreTrackingListener.
 *
 * @author Guy Shoham
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * class constructor.
     *
     * @param scoreCounter counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumberOfHits() == 1) {
            currentScore.increase(10);
        } else {
            currentScore.increase(5);
        }
        System.out.println(currentScore.getValue());
    }
}