package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * class BackgroundDirectHit.
 *
 * @author Guy Shoham
 */
public class BackgroundDirectHit extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width = 800, height = 600;

    /**
     * Class Constructor.
     */
    public BackgroundDirectHit() {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));

        surface.setColor(Color.BLUE);
        surface.drawLine(260, 181, 380, 181);
        surface.drawLine(541, 181, 419, 181);
        surface.drawLine(400, 56, 400, 180);
        surface.drawLine(400, 200, 400, 327);

        surface.drawCircle(400, 181, 60);
        surface.drawCircle(400, 181, 90);
        surface.drawCircle(400, 181, 120);
    }
}
