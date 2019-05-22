package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * class BackgroundGreenThree.
 *
 * @author Guy Shoham
 */
public class BackgroundGreenThree extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width, height;

    /**
     * Class Constructor.
     */
    public BackgroundGreenThree() {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
        this.width = 800;
        this.height = 600;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GREEN.darker().darker());
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));

        surface.setColor(Color.WHITE);
        surface.fillRectangle(75, 450, 100, 150);
        surface.setColor(Color.BLACK);
        //windows
        surface.fillRectangle(75, 450, 100, 10);
        surface.fillRectangle(75, 480, 100, 10);
        surface.fillRectangle(75, 510, 100, 10);
        surface.fillRectangle(75, 540, 100, 10);
        surface.fillRectangle(75, 570, 100, 10);
        surface.fillRectangle(75, 450, 10, 150);
        surface.fillRectangle(100, 450, 10, 150);
        surface.fillRectangle(125, 450, 10, 150);
        surface.fillRectangle(150, 450, 10, 150);
        surface.fillRectangle(175, 450, 10, 150);
        //tower
        surface.setColor(Color.DARK_GRAY);
        surface.fillRectangle(115, 400, 30, 50);
        surface.setColor(new Color(78, 74, 73));
        surface.fillRectangle(125, 200, 10, 200);
        //circles
        surface.setColor(new Color(182, 163, 86));
        surface.fillCircle(130, 190, 12);
        surface.setColor(new Color(245, 77, 54));
        surface.fillCircle(130, 190, 8);
        surface.setColor(Color.WHITE);
        surface.fillCircle(130, 190, 4);
    }
}
