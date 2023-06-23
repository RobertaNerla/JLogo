package it.unicam.cs.pa.JLogoApp.model;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class DefaultWorksheet<L extends Line<SimpleCoordinate>> implements Worksheet<SimpleCoordinate, L> {
    private final double width;
    private final double height;
    private final SimpleCoordinate homePoint;
    private Color backgroundColor;
    private List<L> lines;


    private final List<Figure<L, SimpleCoordinate>> figures;

    /**
     * Default constructor for a worksheet.
     *
     * @param width width of the worksheet.
     * @param height height of the worksheet.
     */
    public DefaultWorksheet(double width, double height){
        this(width,height, Color.white);
    }

    public DefaultWorksheet(double width, double height, Color backgroundColor) {
        this.width = width;
        this.height = height;
        homePoint = new SimpleCoordinate(width/2, height/2);
        this.backgroundColor = backgroundColor;
        lines = new LinkedList<>();
        figures = new LinkedList<>();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public SimpleCoordinate getHomePoint() {
        return homePoint;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setColorBackground(Color color) {
        this.backgroundColor = color;
    }

    private boolean isValidCoordinates(SimpleCoordinate c) {
        return (c.getX()>=0)&&(c.getX()<=width)&&
                (c.getY()>=0)&&(c.getY()< height);
    }

    private boolean isValidXCoordinate(SimpleCoordinate c){
        return c.getX()<=width;
    }

    private boolean isValidYCoordinate(SimpleCoordinate c){
        return c.getY()<=height;
    }

    @Override
    public Predicate<? super SimpleCoordinate> getBoundaryPredicate() {
        return this::isValidCoordinates;
    }

    @Override
    public Predicate<? super SimpleCoordinate> getWidthBoundaryPredicate(){
        return this::isValidXCoordinate;
    }

    @Override
    public Predicate<? super SimpleCoordinate> getHeightBoundaryPredicate(){
        return this::isValidYCoordinate;
    }

    @Override
    public boolean addLineToWorksheet(L line) {
        return lines.add(line);
    }

    @Override
    public boolean addFigureToWorksheet(Figure<L, SimpleCoordinate> figure) {
        return figures.add(figure);
    }


    @Override
    public boolean checkForNewFigure(L line) {
        return lines
                .stream()
                .anyMatch((l) -> l.getFirstExtreme().equals(line.getSecondExtreme()));
    }

    @Override
    public List<L> getFigureLines(int indexLineFigure) {
        List<L> linesFigure = lines.subList(indexLineFigure, lines.size());
        removeLinesOfAFigure(indexLineFigure);
        return linesFigure;
    }

    private void removeLinesOfAFigure(int indexLineFigure){
        setLines(lines.subList(0, indexLineFigure));
    }

    @Override
    public int findIndexFirstLineFigure(L line) throws IllegalArgumentException {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getFirstExtreme().equals(line.getSecondExtreme())) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public List<L> getLines() {
        return lines;
    }

    @Override
    public void setLines(List<L> lines) {
        this.lines = lines;
    }

    @Override
    public List<Figure<L, SimpleCoordinate>> getFigures() {
        return figures;
    }

    @Override
    public void clearWorksheet() {
        lines.clear();
        figures.clear();
    }


    @Override
    public String stringOf() {
        return "SIZE" + " " +
                width + " " +
                height + " " +
                backgroundColor.getRed() + " " +
                backgroundColor.getGreen() + " " +
                backgroundColor.getBlue() + "\n";
    }
}
