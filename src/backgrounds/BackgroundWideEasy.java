package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * class BackgroundWideEasy.
 *
 * @author Guy Shoham
 */
public class BackgroundWideEasy extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width = 800, height = 600;

    /**
     * Class Constructor.
     */
    public BackgroundWideEasy() {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));


        //light rays
        int index = 25;
        surface.setColor(new Color(238, 230, 175));
        while (index < 775) {
            surface.drawLine(150, 150, index, 275);
            index = index + 5;
        }

        //sun
        surface.setColor(new Color(238, 230, 175));
        surface.fillCircle(150, 150, 60);
        surface.setColor(new Color(235, 214, 73));
        surface.fillCircle(150, 150, 50);
        surface.setColor(new Color(253, 224, 24));
        surface.fillCircle(150, 150, 40);
    }
}
