package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import backend.Sprite;

import java.awt.Image;

/**
 * class BackgroundImage.
 *
 * @author Guy Shoham
 */
public class BackgroundImage extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width = 800, height = 600;
    private Image image;

    /**
     * Class Constructor.
     *
     * @param image image
     */
    public BackgroundImage(Image image) {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.drawImage((int) upperLeft.getX(), (int) upperLeft.getY(), image);
    }
}
