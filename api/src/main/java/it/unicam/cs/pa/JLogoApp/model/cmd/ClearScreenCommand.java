package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.*;

/**
 * This command is used to delete all lines and figures in a Logo worksheet.
 * @param <C> type for a generic coordinates system.
 * @param <L> type for a generic line.
 */
public class ClearScreenCommand<C extends Coordinate<C>, L extends Line<C>> extends AbstractCommandWithName{

    private final Worksheet<C,L> worksheet;

    public ClearScreenCommand(Worksheet<C,L> worksheet) {
        super(CommandName.CLEARSCREEEN);
        this.worksheet = worksheet;
    }

    @Override
    public void execute() {
        worksheet.clearWorksheet();
    }

    @Override
    public void setParamFromString(String[] params) {

    }

}
