package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

import java.awt.*;

/**
 * This command is used to change the color of the figures that were drawn by the Logo turtle.
 * @param <C> type for a generic coordinate system.
 */
public class SetFillColorCommand<C extends Coordinate<C>, L extends Line<C>, R> extends ColoringCommand {

    private final Turtle<C,L,R> turtle;

    public SetFillColorCommand(Turtle<C,L,R> turtle){
        super(CommandName.SETFILLCOLOR,turtle.getColorLine());
        this.turtle = turtle;
    }

    public SetFillColorCommand(Turtle<C,L,R> turtle, int r, int g, int b) {
        super(CommandName.SETFILLCOLOR, new Color(r,g,b));
        this.turtle=turtle;
    }

    @Override
    public void execute() {
        turtle.setColorArea(getColorParameter());
    }
}
