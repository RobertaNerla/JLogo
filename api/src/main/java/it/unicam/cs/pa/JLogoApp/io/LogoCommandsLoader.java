package it.unicam.cs.pa.JLogoApp.io;

import it.unicam.cs.pa.JLogoApp.model.cmd.Command;

import java.io.IOException;
import java.util.List;

/**
 * A loader used to load a commands list. This loader assumes that the commands are listed, one per line in the file.
 * @param <C> type of generic commands
 */
public class LogoCommandsLoader<C extends Command> implements CommandsLoader<C>{

    private final CommandReader<C> lineParser;

    /**
     * Creates a loader that parses each line with the given function.
     *
     * @param lineParser parser used to read each command.
     */
    public LogoCommandsLoader(CommandReader<C> lineParser) {
        this.lineParser = lineParser;
    }

    @Override
    public void parse(String content, List<C> commands) throws IOException {
        String[] lines = content.split("\n");
        for (String line : lines) {
            Command command = lineParser.parse(line.trim(),commands);
            command.execute();
        }
    }
}
