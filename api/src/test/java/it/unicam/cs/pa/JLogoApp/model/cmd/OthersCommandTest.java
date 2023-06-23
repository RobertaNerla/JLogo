package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OthersCommandTest {

    DefaultTurtle turtle = new DefaultTurtle();
    DefaultWorksheet<Segment<SimpleCoordinate>> w = turtle.getWorksheet();

    ClearScreenCommand<SimpleCoordinate, Segment<SimpleCoordinate>> clearCmd = new ClearScreenCommand<>(w);
    HomeCommand<SimpleCoordinate,Segment<SimpleCoordinate>,Integer> homeCmd = new HomeCommand<>(turtle);
    PenUpCommand<SimpleCoordinate,Segment<SimpleCoordinate>,Integer> penUpCmd = new PenUpCommand<>(turtle);
    PenDownCommand<SimpleCoordinate,Segment<SimpleCoordinate>,Integer> penDownCmd = new PenDownCommand<>(turtle);
    SetPenSizeCommand<SimpleCoordinate,Segment<SimpleCoordinate>,Integer> penSizeCmd = new SetPenSizeCommand<>(turtle);

    @Test
    void shouldClearWorksheet(){
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(250,250),new SimpleCoordinate(300,250)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(300,250),new SimpleCoordinate(300,300)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(300,300),new SimpleCoordinate(350,300)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(350,300),new SimpleCoordinate(350,350)));
        w.addLineToWorksheet(new Segment<>(new SimpleCoordinate(350,350),new SimpleCoordinate(380,483)));
        clearCmd.execute();
    }

    @Test
    void turtleShouldGoToHome(){
        homeCmd.execute();
        assertEquals(new SimpleCoordinate(250,250), turtle.getPosition());
    }

    @Test
    void penUpAndPenDownTest(){
        penUpCmd.execute();
        assertFalse(turtle.getPlot());
        penDownCmd.execute();
        assertTrue(turtle.getPlot());
    }

    @Test
    void penSizeCmdTest(){
        assertEquals(1, turtle.getPenSize());
        String[] str = {"SETPENSIZE", "10","2300"};
        penSizeCmd.setParamFromString(str);
        penSizeCmd.execute();
        assertEquals(10, turtle.getPenSize());
    }

    @Test

    void repeatCmdTest(){

    }


}
