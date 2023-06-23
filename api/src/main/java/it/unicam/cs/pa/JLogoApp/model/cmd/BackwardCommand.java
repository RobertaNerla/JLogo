package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

import java.util.stream.Stream;

/**
 * This command is used to move backward a generic Logo turtle.
 * @param <C> type for a generic coordinates system.
 */
public class BackwardCommand<C extends Coordinate<C>, L extends Line<C>> extends MotionCommand<C,L,Integer>{

    private Integer shift;


    public BackwardCommand(Turtle<C,L,Integer> turtle){
        super(CommandName.BACKWARD, turtle);
        setFunctionSetParam(this::setShiftParameter);
        setFunctionGetParam((params) -> Stream.of(params).map(Integer::parseInt).findFirst());
        shift = null;
    }

    public BackwardCommand(Turtle<C,L,Integer> turtle, Integer shift){
        this(turtle);
        this.shift = shift;
    }


    @Override
    public void execute() {
        getTurtle().move(this::mover,-shift);
    }

    public void setShiftParameter(Integer shift) {
            this.shift = shift;
    }

    public Integer getShiftParameter() {
        return shift;
    }
}
