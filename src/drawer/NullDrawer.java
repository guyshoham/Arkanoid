package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

public class NullDrawer implements Drawer {

    public NullDrawer() {

    }

    @Override
    public void drawAt(DrawSurface d, Rectangle rect) {
    }
}