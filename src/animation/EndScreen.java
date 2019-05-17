package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean isWon;
    private int score;

    public EndScreen(KeyboardSensor k, boolean isWon, int score) {
        this.keyboard = k;
        this.stop = false;
        this.isWon = isWon;
        this.score = score;
    }

    public void doOneFrame(DrawSurface d) {
        if (isWon) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your Score is " + score, 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    public boolean shouldStop() {
        return this.stop;
    }
}