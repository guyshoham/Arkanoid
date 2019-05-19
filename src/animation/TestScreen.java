package animation;

import biuoop.DrawSurface;

public class TestScreen extends KeyPressStoppableAnimation {

    public TestScreen(Animation decorated) {
        super(decorated);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "TEST TEST TEST TEST ", 32);
        super.doOneFrame(d);
    }
}