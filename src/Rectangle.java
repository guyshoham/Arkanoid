import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Rectangle {

    private Point upperLeft, upperRight, lowerLeft, lowerRight;
    private Line topEdge, bottomEdge, leftEdge, rightEdge;
    private double width, height;
    private Color color;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
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

    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) (upperRight.getX() - upperLeft.getX()), (int) (upperRight.getX() - lowerRight.getX()));
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
    // Return a (possibly empty) List of intersection points
    // with the specified line.

    public List intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();

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

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "upperLeft=" + upperLeft +
                ", upperRight=" + upperRight +
                ", lowerLeft=" + lowerLeft +
                ", lowerRight=" + lowerRight +
                ", topEdge=" + topEdge +
                ", bottomEdge=" + bottomEdge +
                ", leftEdge=" + leftEdge +
                ", rightEdge=" + rightEdge +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}