package animation;

import biuoop.DrawSurface;

public class AnimationDecorator implements Animation {

    private Animation decorated;

    protected AnimationDecorator(Animation decorated) {
        this.decorated = decorated;
    }

    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);
    }

    public boolean shouldStop() {
        return this.decorated.shouldStop();
    }
}