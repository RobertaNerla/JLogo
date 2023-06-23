package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

import java.util.stream.Stream;

/**
 * This command is used to rotate a generic Logo turtle to the right;
 * @param <C> type for a generic coordinate system.
 */
public class RightCommand<C extends Coordinate<C>, L extends Line<C>> extends MotionCommand<C,L,Integer>{

    private Integer angle;


    public RightCommand(Turtle<C,L,Integer> turtle) {
        super(CommandName.RIGHT, turtle);
        setFunctionSetParam(this::setAngleParameter);
        setFunctionGetParam((params) -> Stream.of(params).map(Integer::parseInt).findFirst());
        angle = null;
    }

    public RightCommand(Turtle<C,L,Integer> turtle, int angle){
        this(turtle);
        this.angle = angle;
    }


    @Override
    public void execute() {
        getTurtle().rotate(-angle);
    }

    /**
     * Sets the angle variation for the new direction.
     *
     * @param angle the angle variation for the new direction.
     */
    void setAngleParameter(int angle){
        this.angle = angle;
    }

    /**
     * Returns the angle parameter of this command.
     *
     * @return the angle parameter of this command.
     */
    int getAngleParameter(){
        return angle;
    }


}
