package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.io.CommandReader;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * This interface is used to represent a generic command.
 */
public interface Command {

    /**
     * Defines a function that interprets commands.
     */
    CommandReader<Command> commandParser = Command::fromStringCommand;

    /**
     * Starts the command execution.
     */
    void execute();

    /**
     * Returns the name of the command.
     *
     * @return the name of the command.
     */
    CommandName getNameCommand();

    /**
     * Parses the given string that contains the parameters for executing the command
     * and sets them in the command.
     *
     * @param strParams string of parameters.
     */
    void setParamFromString(String[] strParams);

    /**
     * Returns the command associated with the given string.
     *
     * @param cmd a string containing a command.
     * @param commands a list of available command for this application.
     * @return the command associated with the given string.
     * @throws IOException if the string is not correct.
     */
    public static Command fromStringCommand(String cmd,List<Command> commands) throws IOException {
        String[] elem = cmd.split(" ");
        Optional<Command> cmdFound = findCommand(elem, commands);
        cmdFound.ifPresent(command -> command.setParamFromString(elem));
        if(cmdFound.isPresent()){
            return cmdFound.get();
        } else {
            throw new IOException("Command unknown:" + elem[0] +". \n The available commands are: "
                    + commands.stream().map(Command::getNameCommand).toList());
        }
    }

    private static Optional<Command> findCommand(String[] elem,List<Command> commands){
        return commands.stream()
                .filter((c) -> c.getNameCommand().toString().equals(elem[0].toUpperCase()))
                .findFirst();
    }

}
