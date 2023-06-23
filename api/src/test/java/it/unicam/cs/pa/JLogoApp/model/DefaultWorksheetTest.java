package it.unicam.cs.pa.JLogoApp.model;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultWorksheetTest {

    DefaultWorksheet<Segment<SimpleCoordinate>> w = new DefaultWorksheet<>(500,500);



    @Test
    void testAddLineToWorksheet(){
        boolean result = w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(2,3), new SimpleCoordinate(4,6)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(4,6), new SimpleCoordinate(10,6)));
        assertTrue(result);
        assertEquals(w.getLines().size(),2);
    }

    @Test
    void testGetBoundaryPredicate(){
        assertTrue(w.getBoundaryPredicate().test(new SimpleCoordinate(500,499)));
        assertFalse(w.getBoundaryPredicate().test(new SimpleCoordinate(501,501)));
    }

    @Test
    void testCheckForNewFigure(){
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(2,3), new SimpleCoordinate(4,6)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(4,6), new SimpleCoordinate(10,6)));
        Segment<SimpleCoordinate> s = new Segment<>(new SimpleCoordinate(10,6), new SimpleCoordinate(2,3));
        w.addLineToWorksheet(s);
        assertEquals(3,w.getLines().size());
        assertTrue(w.checkForNewFigure(s));
    }

    @Test
    void testFindIndexFirstLineFigure(){
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(0,1), new SimpleCoordinate(1,1)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(1,1), new SimpleCoordinate(2,3)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(2,3), new SimpleCoordinate(4,6)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(4,6), new SimpleCoordinate(10,6)));
        Segment<SimpleCoordinate> s = new Segment<>(new SimpleCoordinate(10,6), new SimpleCoordinate(2,3));
        w.addLineToWorksheet(s);
        assertEquals(5,w.getLines().size());
        assertEquals(2, w.findIndexFirstLineFigure(s));
    }

    @Test
    void testGetFigureLines(){
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(1,1), new SimpleCoordinate(2,3)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(2,3), new SimpleCoordinate(4,6)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(4,6), new SimpleCoordinate(10,6)));
        Segment<SimpleCoordinate> s = new Segment<>(new SimpleCoordinate(10,6), new SimpleCoordinate(2,3));
        w.addLineToWorksheet(s);
        w.checkForNewFigure(s);
        List<Segment<SimpleCoordinate>>lf = w.getFigureLines(w.findIndexFirstLineFigure(s));
        assertEquals(3, lf.size());
        assertEquals(1, w.getLines().size());
    }

}