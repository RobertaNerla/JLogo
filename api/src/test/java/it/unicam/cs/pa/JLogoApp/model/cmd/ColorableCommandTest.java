package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.DefaultTurtle;
import it.unicam.cs.pa.JLogoApp.model.DefaultWorksheet;
import it.unicam.cs.pa.JLogoApp.model.Segment;
import it.unicam.cs.pa.JLogoApp.model.SimpleCoordinate;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorableCommandTest {

    DefaultTurtle turtle = new DefaultTurtle();

    SetPenColorCommand<SimpleCoordinate, Segment<SimpleCoordinate>,Integer> penColorCmd = new SetPenColorCommand<>(turtle);
    SetFillColorCommand<SimpleCoordinate,Segment<SimpleCoordinate>,Integer> fillColorCmd = new SetFillColorCommand<>(turtle);
    SetScreenColorCommand<SimpleCoordinate, Segment<SimpleCoordinate>> screenColorCmd = new SetScreenColorCommand<>(turtle.getWorksheet());

    @Test
    void testPenColorSetParamFromString(){
        String[] strParam = {"SETPENCOLOR","255","255","255"};
        penColorCmd.setParamFromString(strParam);
        penColorCmd.execute();
        assertEquals(Color.white, turtle.getColorLine());
    }

    @Test
    void testFillColorSetParamFromString(){
        String[] strParam = {"SETFILLCOLOR","0","0","0"};
        fillColorCmd.setParamFromString(strParam);
        fillColorCmd.execute();
        assertEquals(Color.black, turtle.getColorArea());
    }

    @Test

    void testScreenColorSetParamFromString(){
        String[] strParam = {"SETSCREENCOLOR","0","0","0"};
        screenColorCmd.setParamFromString(strParam);
        screenColorCmd.execute();
        assertEquals(Color.black, turtle.getWorksheet().getBackgroundColor());
    }






}