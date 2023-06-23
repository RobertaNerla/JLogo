package it.unicam.cs.pa.JLogoApp.io;

import it.unicam.cs.pa.JLogoApp.model.cmd.Command;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface CommandReader<C extends Command> {
    /**
     * Returns the command associated with the given string.
     *
     * @param str a string containing a command.
     * @return the command associated with the given string.
     * @throws IOException if the string is not correct.
     */
    C parse(String str,List<C> commands) throws IOException;

}
