package geometry;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background Model Object.
 *
 * @author Guy Shoham
 */
public class Background extends Rectangle implements Sprite {

    private Point upperLeft;
    private double width, height;
    private Color color;

    /**
     * Class Constructor.
     * Creates a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Background(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = Color.BLUE.darker();
    }

    /**
     * draw the rectangle on the drawSurface.
     *
     * @param surface surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width), (int) (height));

        surface.setColor(Color.WHITE);
        surface.drawText(350, (int) (upperLeft.getY() + (height / 10 * 7)), "Arkanoid", 40);
    }
}