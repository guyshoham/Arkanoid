package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen extends KeyPressStoppableAnimation {
    private boolean isWon;
    private int score;

    public EndScreen(KeyboardSensor sensor, String key, boolean isWon, int score, Animation decorated) {
        super(sensor, key, decorated);
        this.isWon = isWon;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWon) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your Score is " + score, 32);
        }
        super.doOneFrame(d);
    }
}