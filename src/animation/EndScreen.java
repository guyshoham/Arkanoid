package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen extends KeyPressStoppableAnimation implements Animation {
    private boolean isWon;
    private int score;

    public EndScreen(KeyboardSensor k, boolean isWon, int score) {
        super(k, KeyboardSensor.SPACE_KEY);
        this.isWon = isWon;
        this.score = score;
    }

    public void doOneFrame(DrawSurface d) {
        if (isWon) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your Score is " + score, 32);
        }
        super.doOneFrame(d);
    }

    public boolean shouldStop() {
        return super.shouldStop();
    }
}