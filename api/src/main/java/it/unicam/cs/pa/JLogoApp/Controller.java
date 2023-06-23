package it.unicam.cs.pa.JLogoApp;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;
import it.unicam.cs.pa.JLogoApp.model.cmd.Command;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This interface is used to control the activities around a Logo execution.
 */
public interface Controller<C extends Coordinate<C>,L extends Line<C>,R> {

    /**
     * Returns a list with available commands for a Logo application.
     * @param turtle the current turtle.
     * @return a list with available commands for a Logo application
     */
    public List<Command> availableCommands(Turtle<C,L,R> turtle);

    /**
     * Loads and executes a list of commands from file.
     *
     * @param file file from which we can load a list of commands.
     * @throws IOException if an I/O error occurs while loading the data.
     */
    public void executeCommandsFromFile(File file) throws IOException;

    /**
     * Writes the execution of JlogoApp to the given file.
     *
     * @param file file on which we can save the environment.
     * @throws IOException if an I/O error occurs while writing the data.
     */
    public void save(File file) throws IOException;

    /**
     * This method is used to reset the state of this controller.
     */
    public void reset();
}
