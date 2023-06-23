package it.unicam.cs.pa.JLogoApp.io;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Worksheet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This is a functional interface that is used to write the worksheet into a String.
 *
 * @param <C> type for a generic coordinates system.
 * @param <L> type for a generic line.
 */

@FunctionalInterface
public interface ExecutionWriter<C extends Coordinate<C>, L extends Line<C>> {
    /**
     * Returns the string representing the given worksheet and what it contains.
     *
     * @param worksheet a worksheet.
     * @return the string representing the given worksheet and what it contains.
     */
    String stringOf(Worksheet<C,L> worksheet);

    /**
     * Writes the given worksheet and what it contains in the file referenced by the given path.
     *
     * @param path the path where the worksheet is saved.
     * @param worksheet the worksheet to write.
     * @throws IOException if an I/O error occurs while writing the file.
     */
    default void writeTo(Path path, Worksheet<C,L> worksheet) throws IOException{
        Files.write(path, stringOf(worksheet).getBytes());
    }

    /**
     * Writes the given worksheet and what it contains in the given file.
     *
     * @param file the file where the worksheet is saved.
     * @param worksheet the worksheet to write.
     * @throws IOException if an I/O error occurs while writing the file.
     */
    default void writeTo(File file, Worksheet<C,L> worksheet) throws IOException{
        writeTo(file.toPath(), worksheet);
    }
}
