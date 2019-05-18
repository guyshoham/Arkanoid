package animation;

import biuoop.DrawSurface;

public class PauseScreen extends KeyPressStoppableAnimation {

    public PauseScreen(Animation decorated) {
        super(decorated);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        super.doOneFrame(d);
    }
}