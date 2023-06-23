package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

/**
 * This command is used to move a generic Logo turtle to the home point.
 * @param <C> type for a generic coordinates system.
 */
public class HomeCommand<C extends Coordinate<C>,L extends Line<C>, R> extends AbstractCommandWithName{

    private final Turtle<C,L,R> turtle;


    public HomeCommand(Turtle<C,L,R> turtle) {
        super(CommandName.HOME);
        this.turtle = turtle;
    }

    @Override
    public void execute() {
        turtle.moveToHome();
    }

    @Override
    public void setParamFromString(String[] params) {

    }
}
