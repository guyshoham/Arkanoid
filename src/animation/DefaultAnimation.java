package animation;

import biuoop.DrawSurface;

public class DefaultAnimation implements Animation {

    private boolean stop;

    public DefaultAnimation() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.stop = true;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
