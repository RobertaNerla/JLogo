package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.SimpleController;
import it.unicam.cs.pa.JLogoApp.model.Segment;
import it.unicam.cs.pa.JLogoApp.model.SimpleCoordinate;
import it.unicam.cs.pa.JLogoApp.model.Turtle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatCommandTest {

    SimpleController<SimpleCoordinate, Segment<SimpleCoordinate>> controller = SimpleController.getDefaultController();
    Turtle<SimpleCoordinate,Segment<SimpleCoordinate>, Integer> turtle = controller.getCurrentTurtle();

    RepeatCommand<SimpleCoordinate,Segment<SimpleCoordinate>> repCmd = new RepeatCommand<>(controller);

    @Test
    void shouldSetTheRightParamForRepeatCommand(){
        String[] str = {"REPEAT","1","[FORWARD", "50;", "LEFT", "90]"};
        repCmd.setParamFromString(str);
        assertEquals(1, repCmd.getRepParameter());
        assertEquals("FORWARD 50; LEFT 90", repCmd.getCommandsListString());
    }

    @Test
    void TurtleShouldGoForward(){
        String[] str = {"REPEAT","1","[FORWARD", "40;","FORWARD", "50;", "LEFT", "90]"};
        repCmd.setParamFromString(str);
        assertEquals(1, repCmd.getRepParameter());
        assertEquals("FORWARD 40; FORWARD 50; LEFT 90", repCmd.getCommandsListString());
        repCmd.execute();
        assertEquals(340, turtle.getPosition().getX());
        assertEquals(90, turtle.getDirection());

    }



}