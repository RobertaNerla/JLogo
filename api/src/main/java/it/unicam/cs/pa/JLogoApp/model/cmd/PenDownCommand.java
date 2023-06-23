package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

/**
 * This command is used to put down the pen on the worksheet,
 * so that lines or figures will be drawn by the Logo turtle.
 *
 * @param <C> type for a generic coordinate system.
 */
public class PenDownCommand<C extends Coordinate<C>, L extends Line<C>, R> extends AbstractCommandWithName{

    private final Turtle<C,L,R> turtle;

    public PenDownCommand(Turtle<C,L,R> turtle) {
        super(CommandName.PENDOWN);
        this.turtle = turtle;
    }

    @Override
    public void execute() {
        turtle.setPlot(true);
    }

    @Override
    public void setParamFromString(String[] params) {

    }
}
