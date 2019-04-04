import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Point Model Object.
 *
 * @author Guy Shoham
 */
class Rectangle implements Sprite {

    private Point upperLeft, upperRight, lowerLeft, lowerRight;
    private Line topEdge, bottomEdge, leftEdge, rightEdge;
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
    Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.topEdge = new Line(upperLeft, upperRight);
        this.bottomEdge = new Line(lowerLeft, lowerRight);
        this.leftEdge = new Line(upperLeft, lowerLeft);
        this.rightEdge = new Line(upperRight, lowerRight);
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
    }

    /**
     * notify the rectangle that time has passed.
     * currently do nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper right point of the rectangle
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * @return the lower left point of the rectangle
     */
    public Point getLowerLeft() {
        return lowerLeft;
    }

    /**
     * @return the lower right point of the rectangle
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * @return the top edge line of the rectangle
     */
    public Line getTopEdge() {
        return topEdge;
    }

    /**
     * @return the bottom edge line of the rectangle
     */
    public Line getBottomEdge() {
        return bottomEdge;
    }

    /**
     * @return the left edge line of the rectangle
     */
    public Line getLeftEdge() {
        return leftEdge;
    }

    /**
     * @return the right edge line of the rectangle
     */
    public Line getRightEdge() {
        return rightEdge;
    }


    /**
     * @param line line.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();

        //check intersection point with each edge of the rectangle
        if (line.isIntersecting(topEdge)) {
            points.add(line.intersectionWith(topEdge));
        }
        if (line.isIntersecting(bottomEdge)) {
            points.add(line.intersectionWith(bottomEdge));
        }
        if (line.isIntersecting(leftEdge)) {
            points.add(line.intersectionWith(leftEdge));
        }
        if (line.isIntersecting(rightEdge)) {
            points.add(line.intersectionWith(rightEdge));
        }

        return points;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * sets the color of the rectangle.
     *
     * @param newColor color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "upperLeft=" + upperLeft + ", upperRight=" + upperRight + ", lowerLeft=" + lowerLeft
                + ", lowerRight=" + lowerRight + ", topEdge=" + topEdge + ", bottomEdge=" + bottomEdge
                + ", leftEdge=" + leftEdge + ", rightEdge=" + rightEdge + ", width=" + width
                + ", height=" + height + '}';
    }

    /**
     * add the block to the game.
     *
     * @param g game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}