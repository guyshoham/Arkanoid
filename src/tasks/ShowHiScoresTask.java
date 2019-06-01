package tasks;

import animation.Animation;
import animation.AnimationRunner;

/**
 * class ShowHiScoresTask.
 *
 * @author Guy Shoham
 */

public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;

    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    public Void run() {
        runner.run(highScoresAnimation);
        return null;
    }
}