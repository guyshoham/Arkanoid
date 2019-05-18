package animation;

import biuoop.DrawSurface;

public class EndScreen extends KeyPressStoppableAnimation {
    private boolean isWon;
    private int score;

    public EndScreen(boolean isWon, int score, Animation decorated) {
        super(decorated);
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