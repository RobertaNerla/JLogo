package it.unicam.cs.pa.JLogoApp.model;

import java.util.Objects;

public class SimpleCoordinate implements Coordinate<SimpleCoordinate> {

    private final double x;
    private final double y;


    /**
     * Constructor for default coordinates.
     *
     * @param x x coordinate for a point.
     * @param y y coordinate for a point.
     */
    public SimpleCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public SimpleCoordinate nextCoordinate(int dl, int angle) {
        double newX = nextX(dl,angle);
        double newY = nextY(dl,angle);
        if (newX < 0){
            newX = 0;
        }
        if (newY < 0){
            newY = 0;
        }
        return new SimpleCoordinate(newX,newY);
    }

    private double nextX(int dl, int angle){
        return (Math.round(this.x + dl*Math.cos(Math.toRadians(angle))*100/100));
    }

    private double nextY(int dl, int angle){
        return (Math.round(this.y + dl*Math.sin(Math.toRadians(angle))*100/100
        ));
    }

    @Override
    public SimpleCoordinate nextCoordinateOnXBound(double width){
        return new SimpleCoordinate(width, this.y);
    }

    @Override
    public SimpleCoordinate nextCoordinateOnYBound(double height){
        return new SimpleCoordinate(this.x, height);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point.
     */
    public double getY() {
        return y;
    }

    @Override
    public String stringOf() {
        return x + " " + y + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleCoordinate that = (SimpleCoordinate) o;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "SimpleCoordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
