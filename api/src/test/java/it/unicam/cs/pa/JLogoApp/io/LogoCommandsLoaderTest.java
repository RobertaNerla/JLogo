package it.unicam.cs.pa.JLogoApp.io;

import it.unicam.cs.pa.JLogoApp.SimpleController;
import it.unicam.cs.pa.JLogoApp.model.Segment;
import it.unicam.cs.pa.JLogoApp.model.SimpleCoordinate;
import it.unicam.cs.pa.JLogoApp.model.Turtle;
import it.unicam.cs.pa.JLogoApp.model.cmd.Command;
import it.unicam.cs.pa.JLogoApp.model.cmd.ForwardCommand;
import it.unicam.cs.pa.JLogoApp.model.cmd.LeftCommand;
import it.unicam.cs.pa.JLogoApp.model.cmd.RepeatCommand;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogoCommandsLoaderTest {


    SimpleController<SimpleCoordinate, Segment<SimpleCoordinate>> controller = SimpleController.getDefaultController();
    Turtle<SimpleCoordinate, Segment<SimpleCoordinate>, Integer> turtle = controller.getCurrentTurtle();
    List<Command> commands = Arrays.asList(new ForwardCommand<>(controller.getCurrentTurtle()), new LeftCommand<>(controller.getCurrentTurtle()), new RepeatCommand<>(controller));
    CommandReader<Command> lineParser = Command::fromStringCommand;
    LogoCommandsLoader<Command> loader = new LogoCommandsLoader<>(lineParser);

    @Test
    void testShouldReadTheCommand(){
        try{
            Command readCmd = Command.fromStringCommand("Forward 40 50", commands);
        }catch(IOException e){

        }
        commands.get(0).execute();
        assertEquals(290, turtle.getPosition().getX());
    }

    @Test
    void testShouldLaunchAnIOException(){
        boolean thrown = false;
        try{
            Command readCmd = Command.fromStringCommand("Right 40", commands);
        }catch(IOException e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void testShouldExecuteStringOfCommands(){
        try{
            loader.parse("Forward 40 \n Left 90",commands);
        }catch (IOException e){

        }
        assertEquals(290, turtle.getPosition().getX());
        assertEquals(90, turtle.getDirection());

    }

    @Test
    void testShouldExecuteRepeatCommand(){
        try{
            loader.parse("Repeat 1 [Forward 40; Left 90]",commands);
        }catch (IOException e){

        }
        assertEquals(290, controller.getCurrentTurtle().getPosition().getX());
        assertEquals(90, controller.getCurrentTurtle().getDirection());

    }





}