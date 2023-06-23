package it.unicam.cs.pa.JLogoApp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCoordinateTest {

    SimpleCoordinate c = new SimpleCoordinate(0,0);
    DefaultWorksheet<Line<SimpleCoordinate>> w = new DefaultWorksheet<>(500,500);


    @Test
    void testNextCoordinate(){
        SimpleCoordinate c2 = c.nextCoordinate(60, 90);
        assertEquals(60, c2.getY());
    }

    @Test
    void testNextCoordinateWithPred(){
        SimpleCoordinate c2 = c.nextCoordinate(60, 90, w.getBoundaryPredicate());
        assertEquals( 60, c2.getY());
    }


    @Test
    public void shouldLaunchIllegalArgumentException() {
        boolean thrown = false;

        try {
            c.nextCoordinate(600,90,w.getBoundaryPredicate());
        } catch (IllegalArgumentException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

}