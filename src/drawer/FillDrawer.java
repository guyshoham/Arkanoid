package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Class FillDrawer.
 *
 * @author Guy Shoham
 */
public class FillDrawer implements Drawer {

    private Color color;


    /**
     * Class constructor.
     *
     * @param color color
     */
    public FillDrawer(Color color) {
        this.color = color;
    }

    @Override
    public void draw(DrawSurface d, Rectangle rect) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
}