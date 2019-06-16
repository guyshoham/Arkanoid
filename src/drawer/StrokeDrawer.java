package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Class StrokeDrawer.
 *
 * @author Guy Shoham
 */
public class StrokeDrawer implements Drawer {

    private Color color;

    /**
     * Class constructor.
     *
     * @param color color
     */
    public StrokeDrawer(Color color) {
        this.color = color;
    }

    @Override
    public void draw(DrawSurface d, Rectangle rect) {
        d.setColor(color);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
}