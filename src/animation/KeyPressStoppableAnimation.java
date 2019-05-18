package animation;

import biuoop.DrawSurface;

public abstract class KeyPressStoppableAnimation implements Animation {

    private Animation decorated;

    protected KeyPressStoppableAnimation(Animation decorated) {
        this.decorated = decorated;
    }

    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);
    }

    public boolean shouldStop() {
        return this.decorated.shouldStop();
    }
}