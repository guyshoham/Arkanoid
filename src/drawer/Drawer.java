package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * Interface Drawer.
 *
 * @author Guy Shoham
 */
public interface Drawer {

    /**
     * draw the rectangle on the surface.
     *
     * @param d    surface
     * @param rect rectangle
     */
    void drawAt(DrawSurface d, Rectangle rect);
}