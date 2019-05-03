package Listeners;

import gameObjects.Ball;
import gameObjects.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

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