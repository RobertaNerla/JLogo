package it.unicam.cs.pa.JLogoApp.model;

import java.awt.*;
import java.util.Objects;

public class Segment<C extends Coordinate<C>> implements Line<C> {

    private final C firstExtreme;
    private final C secondExtreme;
    private final int size;
    private final Color lineColor;


    /**
     * Default constructor for segment class.
     *
     * @param firstExtreme first extreme of the segment.
     * @param secondExtreme second extreme of the segment.
     */
    public Segment(C firstExtreme, C secondExtreme) {
        this(firstExtreme,secondExtreme, 1, Color.BLACK);
    }

    public Segment(C firstExtreme, C secondExtreme,int size, Color lineColor){
        this.firstExtreme = firstExtreme;
        this.secondExtreme = secondExtreme;
        this.size = size;
        this.lineColor = lineColor;
    }

    @Override
    public C getFirstExtreme() {
        return firstExtreme;
    }

    @Override
    public C getSecondExtreme() {
        return secondExtreme;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Color getLineColor() {
        return lineColor;
    }

    @Override
    public String stringOf() {
        return "LINE" + " " +
                firstExtreme.stringOf() + " " +
                secondExtreme.stringOf() + " " +
                lineColor.getRed() + " " +
                lineColor.getGreen() + " " +
                lineColor.getBlue() + " " +
                size + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment<?> segment = (Segment<?>) o;
        return getFirstExtreme().equals(segment.getFirstExtreme()) && getSecondExtreme().equals(segment.getSecondExtreme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstExtreme(), getSecondExtreme(), getSize(), getLineColor());
    }

    @Override
    public String toString() {
        return "Segment{" +
                "firstExtreme=" + firstExtreme +
                ", secondExtreme=" + secondExtreme +
                ", size=" + size +
                ", LineColor=" + lineColor +
                '}';
    }

}
