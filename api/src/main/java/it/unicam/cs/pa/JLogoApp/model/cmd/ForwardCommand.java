package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

import java.util.stream.Stream;

/**
 * This command is used to move forward a generic Logo turtle.
 * @param <C> type for a generic coordinates system.
 */
public class ForwardCommand<C extends Coordinate<C>, L extends Line<C>> extends MotionCommand<C,L,Integer>{

    private Integer shift;


    public ForwardCommand(Turtle<C,L,Integer> turtle){
        super(CommandName.FORWARD, turtle);
        setFunctionSetParam(this::setShiftParameter);
        setFunctionGetParam((params) -> Stream.of(params).map(Integer::parseInt).findFirst());
        shift = null;
    }

    public ForwardCommand(Turtle<C,L,Integer> turtle, Integer shift){
        this(turtle);
        this.shift = shift;
    }


    @Override
    public void execute() {
        getTurtle().move(this::mover, shift);
    }

    /**
     * Sets the shift parameter of this command.
     *
     * @param shift shift
     */
    public void setShiftParameter(Integer shift) {
        this.shift = shift;
    }

    /**
     * Returns the shift parameter of this command.
     *
     * @return the shift parameter of this command.
     */
    public Integer getShiftParameter() {
        return shift;
    }
}
