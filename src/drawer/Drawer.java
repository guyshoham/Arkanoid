package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

public interface Drawer {

    void drawAt(DrawSurface d, Rectangle rect);
}