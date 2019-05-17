package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

public class BackgroundDirectHit extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width, height;

    /**
     * Class Constructor.
     * Creates a new rectangle with location and width/height.
     */
    public BackgroundDirectHit() {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
        this.width = 800;
        this.height = 600;
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
