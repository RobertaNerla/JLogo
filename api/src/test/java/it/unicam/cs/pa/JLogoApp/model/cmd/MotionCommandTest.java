package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.DefaultTurtle;
import it.unicam.cs.pa.JLogoApp.model.DefaultWorksheet;
import it.unicam.cs.pa.JLogoApp.model.Segment;
import it.unicam.cs.pa.JLogoApp.model.SimpleCoordinate;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class MotionCommandTest {

    DefaultTurtle turtle = new DefaultTurtle();

    ForwardCommand<SimpleCoordinate, Segment<SimpleCoordinate>> fdCmd = new ForwardCommand<>(turtle);
    BackwardCommand<SimpleCoordinate, Segment<SimpleCoordinate>> bdCmd = new BackwardCommand<>(turtle);
    LeftCommand<SimpleCoordinate, Segment<SimpleCoordinate>> leftCmd = new LeftCommand<>(turtle);
    RightCommand<SimpleCoordinate,Segment<SimpleCoordinate>> rightCmd = new RightCommand<>(turtle);

    @Test
    void shouldSetTheRightParamForMotionCommand(){
        String[] str = {"FORWARD","40","50"};
        fdCmd.setParamFromString(str);
        assertEquals(40, fdCmd.getShiftParameter());
    }

    @Test
    void TurtleShouldGoForward(){
        fdCmd.setShiftParameter(50);
        fdCmd.execute();
        assertEquals(300,turtle.getPosition().getX());
    }

    @Test
    void TurtleShouldGoForwardToXBound(){
        fdCmd.setShiftParameter(1000);
        fdCmd.execute();
        assertEquals(500,turtle.getPosition().getX());
    }

    @Test
    void TurtleShouldGoForwardToYBound(){
        leftCmd.setAngleParameter(90);
        leftCmd.execute();
        fdCmd.setShiftParameter(1000);
        fdCmd.execute();
        assertEquals(500,turtle.getPosition().getY());
        assertEquals(250,turtle.getPosition().getX());
    }

    @Test
    void TurtleShouldGoBackward(){
        bdCmd.setShiftParameter(50);
        bdCmd.execute();
        assertEquals(200, turtle.getPosition().getX());
    }

    @Test
    void TurtleShouldGoToZeroX(){
        bdCmd.setShiftParameter(500);
        bdCmd.execute();
        assertEquals(0,turtle.getPosition().getX());
    }

    @Test
    void TurtleShouldGoToZeroY(){
        leftCmd.setAngleParameter(90);
        leftCmd.execute();
        bdCmd.setShiftParameter(500);
        bdCmd.execute();
        assertEquals(0,turtle.getPosition().getY());
        assertEquals(250, turtle.getPosition().getX());
    }

    @Test
    void TurtleShouldRotateToTheLeft(){
        leftCmd.setAngleParameter(450);
        leftCmd.execute();
        assertEquals(90, turtle.getDirection());
    }

    @Test
    void TurtleShouldRotateToTheRight(){
        rightCmd.setAngleParameter(810);
        rightCmd.execute();
        assertEquals(270, turtle.getDirection());
    }

    @Test
    void TurtleShouldDrawANewLine(){
        fdCmd.setShiftParameter(50);
        fdCmd.execute();
        Segment<SimpleCoordinate> s = turtle.getWorksheet().getLines().get(0);
        assertEquals(300, s.getSecondExtreme().getX());
        assertEquals(new Segment<>(new SimpleCoordinate(250,250), new SimpleCoordinate(300,250)), s);
    }

    @Test
    void TurtleShouldDrawASquare(){
        for(int i=0; i<4; i++){
            fdCmd.setShiftParameter(50);
            fdCmd.execute();
            leftCmd.setAngleParameter(90);
            leftCmd.execute();
        }
        int sizeLines = turtle.getWorksheet().getLines().size();
        int sizeFigure = turtle.getWorksheet().getFigures().size();
        assertEquals(0,sizeLines);
        assertEquals(1,sizeFigure);
    }












}