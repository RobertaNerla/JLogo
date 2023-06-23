package it.unicam.cs.pa.JLogoApp.model;

import java.awt.Color;
import java.util.List;
import java.util.function.BiFunction;


public class DefaultTurtle implements Turtle<SimpleCoordinate,Segment<SimpleCoordinate>,Integer>{

    private SimpleCoordinate position;
    private int angle;
    private Color colorLine;
    private Color colorArea;
    private boolean plot;
    private int penSize;
    private final DefaultWorksheet<Segment<SimpleCoordinate>> worksheet;

    /**
     * Default constructor for a Logo turtle
     *
     */
    public DefaultTurtle(){
        this(new SimpleCoordinate(250,250), 0, Color.black, Color.white, true, 1, new DefaultWorksheet<>(500,500));
    }

    public DefaultTurtle(SimpleCoordinate position, int angle, Color colorLine, Color colorArea, boolean plot, int penSize, DefaultWorksheet<Segment<SimpleCoordinate>> worksheet){
        this.position = position;
        this.angle = angle;
        this.colorLine = colorLine;
        this.colorArea = colorArea;
        this.plot = plot;
        this.penSize = penSize;
        this.worksheet = worksheet;
    }

    @Override
    public SimpleCoordinate getPosition() {
        return position;
    }

    @Override
    public void setPosition(SimpleCoordinate position) {
        this.position = position;

    }

    @Override
    public void move(BiFunction<SimpleCoordinate, Integer, SimpleCoordinate> mover, Integer args) {
        SimpleCoordinate oldPosition = this.position;
        setPosition(mover.apply(position,args));
        if(plot){
            Segment<SimpleCoordinate> newSegm = drawLine(Segment::new, oldPosition, position);
            worksheetUpdate(newSegm);
        }
    }

    @Override
    public Segment<SimpleCoordinate> drawLine(BiFunction<SimpleCoordinate, SimpleCoordinate, Segment<SimpleCoordinate>> drawer, SimpleCoordinate f, SimpleCoordinate s) {
        return drawer.apply(f, s);
    }


    @Override
    public void worksheetUpdate(Segment<SimpleCoordinate> segment){
        worksheet.addLineToWorksheet(segment);
        if(worksheet.checkForNewFigure(segment)){
            List<Segment<SimpleCoordinate>> linesFigure = worksheet.getFigureLines(worksheet.findIndexFirstLineFigure(segment));
            worksheet.addFigureToWorksheet(drawFigure(linesFigure));
        }
    }

    @Override
    public Figure<Segment<SimpleCoordinate>, SimpleCoordinate> drawFigure(List<Segment<SimpleCoordinate>> lines) {
        return new Figure<>(lines, colorArea);
    }


    @Override
    public void moveToHome() {
        setPosition(worksheet.getHomePoint());
    }

    @Override
    public int getDirection() {
        return angle;
    }

    @Override
    public void rotate(int angle) {
        this.angle += angle;
        if (this.angle >= MAX_ANGLE_DIRECTION) {
            this.angle %= MAX_ANGLE_DIRECTION;
        }
        if (this.angle < MIN_ANGLE_DIRECTION) {
            this.angle = MAX_ANGLE_DIRECTION - (-angle % MAX_ANGLE_DIRECTION);
        }

    }

    @Override
    public Color getColorLine() {
        return colorLine;
    }

    @Override
    public void setColorLine(Color color) {
        this.colorLine = color;
    }

    @Override
    public Color getColorArea() {
        return colorArea;
    }

    @Override
    public void setColorArea(Color color) {
        this.colorArea = color;
    }

    @Override
    public boolean getPlot() {
        return plot;
    }

    @Override
    public void setPlot(boolean plot) {
        this.plot = plot;
    }

    @Override
    public int getPenSize() {
        return penSize;
    }

    @Override
    public void setPenSize(int size) {
        this.penSize= size;
    }

    @Override
    public DefaultWorksheet<Segment<SimpleCoordinate>> getWorksheet(){
        return worksheet;
    }




}
