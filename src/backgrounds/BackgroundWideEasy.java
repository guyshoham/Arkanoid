package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.*;

public class BackgroundWideEasy extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width, height;

    /**
     * Class Constructor.
     * Creates a new rectangle with location and width/height.
     */
    public BackgroundWideEasy() {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
        this.width = 800;
        this.height = 600;
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
