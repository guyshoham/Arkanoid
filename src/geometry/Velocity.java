package geometry;

/**
 * Velocity Model Object.
 *
 * @author Guy Shoham
 */
public class Velocity {
    private double dx, dy; // the change in position on the `x` and the `y` axes.

    /**
     * Class constructor.
     *
     * @param dx the change of x-axis.
     * @param dy the change of y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle the direction of the velocity.
     * @param speed the speed of the velocity.
     * @return the velocity, based on the angle and speed arguments.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -1 * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return dx the change of x axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * set the dx of the velocity.
     *
     * @param newDx the change of x axis.
     */
    public void setDx(double newDx) {
        dx = newDx;
    }

    /**
     * @return dy the change of yaxis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * set the dy of the velocity.
     *
     * @param newDy the change of y axis.
     */
    public void setDy(double newDy) {
        dy = newDy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param point the point
     * @return the same point with the change of velocity
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + dx, point.getY() + dy);
    }

    /**
     * @param other other velocity.
     * @return true if velocities are equal, false otherwise.
     */
    public boolean equal(Velocity other) {
        return dx == other.dx && dy == other.dy;
    }
}