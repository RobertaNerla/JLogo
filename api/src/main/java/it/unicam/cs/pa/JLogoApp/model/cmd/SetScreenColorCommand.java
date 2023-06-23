package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.*;

import java.awt.*;

/**
 * This command is used to change the background color of a Logo worksheet.
 * @param <C> type for a generic coordinate system.
 * @param <L> type for a generic line.
 */
public class SetScreenColorCommand<C extends Coordinate<C>,L extends Line<C>> extends ColoringCommand {

    private final Worksheet<C,L> worksheet;

    public SetScreenColorCommand(Worksheet<C,L> worksheet){
        super(CommandName.SETSCREENCOLOR,worksheet.getBackgroundColor());
        this.worksheet = worksheet;
    }

    public SetScreenColorCommand(Worksheet<C,L> worksheet, int r, int g, int b) {
        super(CommandName.SETSCREENCOLOR, new Color(r,g,b));
        this.worksheet=worksheet;
    }

    @Override
    public void execute() {
        worksheet.setColorBackground(getColorParameter());
    }
}
