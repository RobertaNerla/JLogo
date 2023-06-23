package it.unicam.cs.pa.JLogoApp;


import it.unicam.cs.pa.JLogoApp.io.LogoCommandsLoader;
import it.unicam.cs.pa.JLogoApp.io.LogoExecutionWriter;
import it.unicam.cs.pa.JLogoApp.model.*;
import it.unicam.cs.pa.JLogoApp.model.cmd.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * This class is used to control the activities around a Logo execution.
 */
public class SimpleController<C extends Coordinate<C>,L extends Line<C>> implements Controller<C,L,Integer>{

    private final LogoExecutionWriter<C,L> writer;
    private final LogoCommandsLoader<Command> loader;

    private final Supplier<Turtle<C,L,Integer>> turtleBuilder;

    private final Turtle<C,L,Integer> currentTurtle;

    public static SimpleController<SimpleCoordinate, Segment<SimpleCoordinate>> getDefaultController(){
        return new SimpleController<>(new LogoExecutionWriter<>(), new LogoCommandsLoader<>(Command::fromStringCommand),
                (Supplier<Turtle<SimpleCoordinate, Segment<SimpleCoordinate>, Integer>>) DefaultTurtle::new);
    }


    /**
     * Creates a new controller that will used the given writer, to save and export results of Logo execution, loader, to read
     * a list of commands from files, and rules to compute execution.
     *
     * @param writer             writer used to save a worksheet on files.
     * @param loader             loader used to load commands from files.
     * @param turtleBuilder      builder used to instantiate the Logo turtle.
     */
    public SimpleController(LogoExecutionWriter<C, L> writer, LogoCommandsLoader<Command> loader, Supplier<Turtle<C, L,Integer>> turtleBuilder) {
        this.writer = writer;
        this.loader = loader;
        this.turtleBuilder = turtleBuilder;
        this.currentTurtle = turtleBuilder.get();

    }

    /**
     * Returns a list with available commands for a Logo application.
     * @param turtle the current turtle.
     * @return a list with available commands for a Logo application
     */
    public List<Command> availableCommands(Turtle<C,L,Integer> turtle){
        return Arrays.asList(
                new ForwardCommand<>(turtle), new BackwardCommand<>(turtle), new LeftCommand<>(turtle), new RightCommand<>(turtle),
                new ClearScreenCommand<>(turtle.getWorksheet()), new PenUpCommand<>(turtle), new PenDownCommand<>(turtle),
                new SetPenColorCommand<>(turtle), new SetFillColorCommand<>(turtle), new SetScreenColorCommand<>(turtle.getWorksheet()),
                new SetPenSizeCommand<>(turtle), new RepeatCommand<>(this));
    }

    /**
     * Loads and executes a list of commands from file.
     *
     * @param file file from which we can load a list of commands.
     * @throws IOException if an I/O error occurs while loading the data.
     */
    public void executeCommandsFromFile(File file) throws IOException {
        loader.parse(file, availableCommands(currentTurtle));
    }

    /**
     * Writes the execution of JlogoApp to the given file.
     *
     * @param file file on which we can save the environment.
     * @throws IOException if an I/O error occurs while writing the data.
     */
    public void save(File file) throws IOException {
        writer.writeTo(file, currentTurtle.getWorksheet());
    }

    /**
     * This method is used to reset the state of this controller.
     */
    public void reset() {
        clearWorksheet();
        moveTurtleToHome();
    }

    private void clearWorksheet(){
        Optional<Command> clearCommand = availableCommands(currentTurtle)
                .stream()
                .filter((c)-> c.getNameCommand().equals(CommandName.CLEARSCREEEN))
                .findFirst();
        clearCommand.ifPresent(Command::execute);
    }

    private void moveTurtleToHome(){
        Optional<Command> homeCommand = availableCommands(currentTurtle)
                .stream()
                .filter((c)-> c.getNameCommand().equals(CommandName.HOME))
                .findFirst();
        homeCommand.ifPresent(Command::execute);
    }

    public Turtle<C, L, Integer> getCurrentTurtle() {
        return currentTurtle;
    }
}