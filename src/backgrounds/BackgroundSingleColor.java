package backgrounds;

import backend.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * class BackgroundSingleColor.
 *
 * @author Guy Shoham
 */
public class BackgroundSingleColor extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width = 800, height = 600;
    private Color color;

    /**
     * Class Constructor.
     *
     * @param color color
     */
    public BackgroundSingleColor(Color color) {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));
    }
}
