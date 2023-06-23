package it.unicam.cs.pa.JLogoApp.model;

import java.awt.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * This interface represents a generic worksheet for a Logo application.
 *
 * @param <C> the type of generic coordinate system.
 * @param <L> the type of generic line.
 */
public interface Worksheet<C extends Coordinate<C>,L extends Line<C>> extends Writable {

    /**
     * Returns the width of the worksheet.
     *
     * @return the width of the worksheet.
     */
    double getWidth();

    /**
     * Returns the height of the worksheet.
     *
     * @return the height of the worksheet.
     */
    double getHeight();

    /**
     * Returns the home point for this worksheet.
     *
     * @return the home point for this worksheet.
     */
    C getHomePoint();

    /**
     * Returns the background color of this worksheet.
     *
     * @return the background color of this worksheet.
     */
    Color getBackgroundColor();

    /**
     * Sets the background color of this worksheet.
     *
     * @param color new color.
     */
    void setColorBackground(Color color);

    /**
     * Returns a predicate that identifies worksheet boundaries.
     *
     * @return a predicate that identifies worksheet boundaries.
     */
    Predicate<? super C> getBoundaryPredicate();

    /**
     * Returns a predicate that control if the x-coordinate of the turtle is out of the worksheet.
     *
     * @return a predicate that control if the x-coordinate of the turtle is out of the worksheet.
     */
    Predicate<? super C> getWidthBoundaryPredicate();

    /**
     * Returns a predicate that control if the y-coordinate of the turtle is out of the worksheet.
     *
     * @return a predicate that control if the y-coordinate of the turtle is out of the worksheet.
     */
    Predicate<? super C> getHeightBoundaryPredicate();

    /**
     * Adds the given line to the worksheet.
     *
     * @param line the line to add.
     * @return true if the process was successful, otherwise false.
     */
    boolean addLineToWorksheet(L line);

    /**
     * Adds the given figure to the worksheet.
     *
     * @param figure figure to add.
     * @return true if the process was successful, otherwise false.
     */
    boolean addFigureToWorksheet(Figure<L,C> figure);

    /**
     * Check if a figure has formed.
     * @param line last drawn line.
     *
     * @return true if a new figure has formed, otherwise false.
     */
    boolean checkForNewFigure(L line);

    /**
     * Returns a list of lines that form a figure.
     *
     * @param indexLineFigure Index of the first line of the figure.
     * @return a list of line that form together a figure.
     */
    List<L> getFigureLines(int indexLineFigure);

    /**
     * Given the last line inserted,
     * it returns the index of the first line of the figure, that has just been created from the last one.
     *
     * @param line the last line inserted that created a figure.
     * @return the index of the first line of the figure just created.
     * @throws IllegalArgumentException if the given line did not create a figure.
     */
    int findIndexFirstLineFigure(L line) throws IllegalArgumentException;

    /**
     * Returns lines that do not compose a figure
     *
     * @return lines that do not compose a figure
     */
    List<L> getLines();

    /**
     * Updates the list of lines in the worksheet.
     *
     * @param lines new list of lines.
     */
    void setLines(List<L> lines);

    /**
     * Returns all the figures in the worksheet.
     *
     * @return all the figures in the worksheet.
     */
    List<Figure<L,C>> getFigures();

    /**
     * This method is used to delete all lines and figures in the worksheet.
     */
    void clearWorksheet();






}
