package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

/**
 * This command is used to lift the pen from the worksheet,
 * so that lines and figures won't be drawn by the Logo turtle.
 *
 * @param <C> type for a generic coordinate system.
 */
public class PenUpCommand<C extends Coordinate<C>,L extends Line<C>, R> extends AbstractCommandWithName{

    private final Turtle<C,L,R> turtle;

    public PenUpCommand(Turtle<C,L,R> turtle) {
        super(CommandName.PENUP);
        this.turtle = turtle;
    }


    @Override
    public void execute() {
        turtle.setPlot(false);
    }

    @Override
    public void setParamFromString(String[] params) {

    }
}
