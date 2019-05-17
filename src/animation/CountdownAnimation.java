package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.*;

public class CountdownAnimation implements Animation {
    private Sleeper sleeper = new Sleeper();
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom, countState;
    private boolean stop;

    // The CountdownAnimation will display the given gameScreen,
    // for numOfSeconds seconds, and on top of them it will show
    // a countdown from countFrom back to 1, where each number will
    // appear on the screen for (numOfSeconds / countFrom) seconds, before
    // it is replaced with the next one.
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.countState = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.PINK);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countState), 50);
        sleeper.sleepFor((long) (numOfSeconds / countFrom));
        countState--;

        if (countState == -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
