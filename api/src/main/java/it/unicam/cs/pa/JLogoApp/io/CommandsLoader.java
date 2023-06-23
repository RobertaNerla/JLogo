package it.unicam.cs.pa.JLogoApp.io;

import it.unicam.cs.pa.JLogoApp.model.cmd.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@FunctionalInterface
public interface CommandsLoader<C extends Command> {
    /**
     * Parse a string that contains a commands list for a Logo application.
     *
     * @param content string containing the commands list.
     *
     * @return the parsed commands list
     * @throws IOException if the string is not properly formed.
     */
    void parse(String content, List<C> commands) throws IOException;

    /**
     * Returns the commands list in the file references by the given path.
     *
     * @param path a path to the file containing a commands list for Logo application.
     * @return the parsed commands list.
     * @throws IOException if an I/O error occurs reading from the file or an unmappable byte sequence is read.
     */
    default void parse(Path path, List<C> commands) throws IOException{
        parse(Files.readString(path), commands);
    }

    /**
     * Returns the commands list stored in the given file.
     *
     *
     * @param file a file containing a commands list for Logo application.
     * @return the commands list described in the file referenced by the given path.
     * @throws IOException if an I/O error occurs reading from the file or an unmappable byte sequence is read.
     */
    default void parse(File file, List<C> commands) throws IOException {
        parse(file.toPath(),commands);
    }

}
