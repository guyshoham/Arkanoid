package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Image;

/**
 * Class ImageDrawer.
 *
 * @author Guy Shoham
 */
public class ImageDrawer implements Drawer {

    private Image image;


    public ImageDrawer(Image img) {
        this.image = img;
    }

    @Override
    public void drawAt(DrawSurface d, Rectangle rect) {
        d.drawImage((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), image);
    }
}