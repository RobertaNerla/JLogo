package it.unicam.cs.pa.JLogoApp.model;


import it.unicam.cs.pa.JLogoApp.model.cmd.Command;

import java.awt.*;
import java.util.List;
import java.util.function.BiFunction;

/**
 * This interface represents a generic turtle (cursor) for a logo application.
 *
 * @param <C> the type for a generic coordinate system.
 * @param <L> the type for a generic line.
 * @param <R> the type of the function argument used to move the turtle.
 */
public interface Turtle< C extends Coordinate<C>, L extends Line<C>,R> {


    /**
     * These constants are used to represent the maximum value for an angle.
     */
    public static final int MAX_ANGLE_DIRECTION = 360;
    /**
     * These constants are used to represent the minimum value for an angle.
     */
    public static final int MIN_ANGLE_DIRECTION = 0;

    /**
     * Returns the current position of the turtle.
     * @return the current position of the turtle.
     */
    C getPosition();

    /**
     * Sets the position of the turtle.
     * @param position position.
     */
    void setPosition(C position);

    /**
     * Moves the turtle to a next position.
     *
     * @param mover function with type: C*R->C
     * @param args parameter for the shift-function that the turtle used to move.
     */
    void move(BiFunction<C,R,C> mover, R args);

    /**
     * Draws a line thanks to the given drawer-line-function
     *
     * @param drawer function with type: C*C->L
     * @param firstExtreme first extreme of the line to pass to the drawer-function
     * @param secondExtreme second extreme of the line to pass to the drawer-function
     * @return a new line.
     */
    L drawLine(BiFunction<C,C,L> drawer, C firstExtreme, C secondExtreme);

    /**
     * Updates the lines and the figures saved in the worksheet by adding the last line created
     * and checking if the last line has formed a new figure.
     * If true, it adds a new figure to the worksheet.
     *
     * @param line the last line drawn.
     */
    void worksheetUpdate(L line);

    /**
     * Creates a figure with the given list of line.
     * @param lines list of lines
     * @return a new figure.
     */
    Figure<L,C> drawFigure(List<L> lines);

    /**
     * Move the turtle to home point.
     */
    void moveToHome();

    /**
     * Returns the current direction of the turtle.
     * @return the current direction of the turtle.
     */
    int getDirection();

    /**
     * Updates the turtle angle after its rotation.
     * @param angle the angle variation for the new direction.
     */
    void rotate(int angle);

    /**
     * Returns the current color of the line that will be drawn by the turtle
     * @return the current color of the lines that will be drawn by the turtle
     */
    Color getColorLine();

    /**
     * Sets the color for the lines that will be drawn by the turtle.
     * @param color color
     */
    void setColorLine(Color color);

    /**
     * Returns the current color for the figures that will be drawn by the turtle.
     * @return the current color fot the figures that will be drawn by the turtle.
     */
    Color getColorArea();

    /**
     * Sets the color for the figures that will be drawn by the turtle.
     * @param color color
     */
    void setColorArea(Color color);

    /**
     * Returns true if during a movement the turtle generates a path, otherwise false.
     * @return true if during a movement the turtle generates a path, otherwise false.
     */
    boolean getPlot();

    /**
     * Sets plot variable to indicates if during a movement the turtle generates a path or not.
     * @param plot indicates if during a movement the turtle generates a path or not.
     */
    void setPlot(boolean plot);

    /**
     * Returns the current pen size of the Logo turtle.
     * @return the current pen size of the Logo turtle.
     */
    int getPenSize();

    /**
     * Sets the pen size of the turtle. The pen size is an integer equals or greater than 1.
     * @param size the pen size.
     */
    void setPenSize(int size);

    /**
     * Returns the worksheet where the turtle is located.
     *
     * @param <W> type for a generic worksheet.
     * @return the worksheet where the turtle is located.
     */
    <W extends Worksheet<C,L>> W getWorksheet();
}
