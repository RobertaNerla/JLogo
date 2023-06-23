package it.unicam.cs.pa.JLogoApp.model;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This record is used to represent a figure that is composed by a sequence of lines.
 *
 * @param lines the lines that make up the figure.
 * @param <L> the type of the lines.
 */
public record Figure<L extends Line<C>, C extends Coordinate<C>>(List<L> lines, Color areaColor) implements Writable {

    @Override
    public String stringOf() {
        return "POLIGON" + " " +
                countLine() + " " +
                areaColor.getRed() + " "
                + areaColor.getGreen() + " "
                + areaColor.getBlue() + "\n"+
                lines.stream().map(Line::stringOfForFigure).collect(Collectors.joining("\n"));
    }

    /**
     * Returns the number of lines that makes up the figure.
     *
     * @return the number of lines that makes up the figure.
     */
    public int countLine(){
        return lines.size();
    }
}
