package it.unicam.cs.pa.JLogoApp.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefaultTurtleTest {

    DefaultTurtle turtle = new DefaultTurtle();

    protected SimpleCoordinate mover(SimpleCoordinate c, int dl) {
        return turtle.getPosition().nextCoordinate(dl, turtle.getDirection(), turtle.getWorksheet().getBoundaryPredicate());
    }

    @Test
    void testMoveAndRotateWithSimpleMoverFunction(){
        turtle.move(this::mover, -700);
        assertEquals(0,turtle.getPosition().getX());
        assertEquals(250, turtle.getPosition().getY());
        turtle.rotate(450);
        assertEquals(90, turtle.getDirection());
        turtle.move(this::mover, -700);
        assertEquals(0, turtle.getPosition().getY());
    }

    @Test
    void testDrawLine(){
        Segment<SimpleCoordinate> s = turtle.drawLine(Segment::new, new SimpleCoordinate(1,0), new SimpleCoordinate(10,5));
        assertEquals(10, s.getSecondExtreme().getX());
    }

    @Test
    void testRotateToRight(){
        turtle.rotate(-90);
        assertEquals(270,turtle.getDirection());
    }



}