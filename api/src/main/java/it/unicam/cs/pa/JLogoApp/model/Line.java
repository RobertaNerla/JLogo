package it.unicam.cs.pa.JLogoApp.model;

import java.awt.*;

/**
 * This interface is used to represent a generic line.
 */
public interface Line<C extends Coordinate<C>> extends Writable{
    /**
     * Returns the coordinates of the first extreme of the line.
     *
     * @return the coordinates of the first extreme of the line.
     */
    C getFirstExtreme();

    /**
     * Returns the coordinates of the second extreme of the line.
     *
     * @return the coordinates of the second extreme of the line.
     */
    C getSecondExtreme();

    /**
     * Returns the size of the line.
     *
     * @return the size of the line.
     */
    int getSize();

    /**
     * Returns the color of the line.
     *
     * @return the color of the line.
     */
    Color getLineColor();

    /**
     * Returns a string that describes a line of a figure.
     *
     * @return a string that describes a line of a figure.
     */
    default String stringOfForFigure(){
        return getFirstExtreme().stringOf() + " " +
                getLineColor().getRed() + " " +
                getLineColor().getGreen() + " " +
                getLineColor().getBlue() + " " +
                getSize() + "\n" ;
    }


}
