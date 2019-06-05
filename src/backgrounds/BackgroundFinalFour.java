package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * class BackgroundFinalFour.
 *
 * @author Guy Shoham
 */
public class BackgroundFinalFour extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width = 800, height = 600;

    /**
     * Class Constructor.
     */
    public BackgroundFinalFour() {
        super(new Point(0, 0), 800, 600);
        this.upperLeft = new Point(0, 0);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(new Color(23, 135, 207));
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));

        //rain left
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 8; i++) {
            surface.drawLine(110 + i * 10, 420, 90 + i * 10, 600);
        }

        //clouds left
        surface.setColor(new Color(203, 203, 203));
        surface.fillCircle(110, 400, 23);
        surface.fillCircle(130, 420, 28);
        surface.setColor(new Color(186, 186, 186));
        surface.fillCircle(145, 395, 28);
        surface.setColor(new Color(169, 169, 169));
        surface.fillCircle(160, 425, 22);
        surface.fillCircle(175, 405, 30);


        //rain right
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 9; i++) {
            surface.drawLine(630 + i * 10, 530, 610 + i * 10, 600);
        }

        //clouds right
        surface.setColor(new Color(203, 203, 203));
        surface.fillCircle(635, 505, 23);
        surface.fillCircle(650, 530, 28);
        surface.setColor(new Color(186, 186, 186));
        surface.fillCircle(665, 510, 28);
        surface.setColor(new Color(169, 169, 169));
        surface.fillCircle(680, 530, 22);
        surface.fillCircle(700, 510, 30);

    }
}
