package it.unicam.cs.pa.JLogoApp.model;

import java.util.function.Predicate;

/**
 * This interface is used to represent a generic coordinates system.
 *
 * @param <C> type of coordinate.
 */
public interface Coordinate<C> extends Writable{
    /**
     * Returns the coordinates of the next point.
     *
     * @param dl the distance from this point to the next.
     * @param angle the angle between the distance vector and the x-axis.
     * @return the coordinates of the next point.
     */
    C nextCoordinate(int dl, int angle);

    /**
     * Returns the coordinates of the next point to the margin of the worksheet referring to the x-axis.
     *
     * @param width the width of the worksheet.
     * @return the coordinates of the next point to the margin of the worksheet referring to the x-axis.
     */
    C nextCoordinateOnXBound(double width);

    /**
     * Returns the coordinates of the next point to the margin of the worksheet referring to the y-axis.
     *
     * @param height the height of the worksheet.
     * @return the coordinates of the next point to the margin of the worksheet referring to the y-axis.
     */
    C nextCoordinateOnYBound(double height);

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point.
     */
    double getX();

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point.
     */
    double getY();

    /**
     * Returns the coordinates of the next point reached, if the turtle didn't exceed the worksheet.
     *
     * @param dl the distance from this point to the next one.
     * @param angle the angle between the distance vector and the x-axis.
     * @param pred the predicate that controls if the turtle didn't exceed the worksheet.
     * @return the coordinates of the next point.
     * @throws IllegalArgumentException if the turtle exceeds the worksheet.
     */
    default C nextCoordinate(int dl, int angle, Predicate<? super C> pred) throws IllegalArgumentException{
        if(pred.test(nextCoordinate(dl,angle))){
            return nextCoordinate(dl, angle);
        } else{
            throw new IllegalArgumentException("The turtle exceeds the worksheet");
        }
    }

}
