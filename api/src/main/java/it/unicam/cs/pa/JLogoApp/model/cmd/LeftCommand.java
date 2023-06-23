package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

import java.util.stream.Stream;

/**
 * This command is used to rotate a Logo turtle to the left.
 * @param <C> type for a generic coordinate system.
 */
public class LeftCommand<C extends Coordinate<C>, L extends Line<C>> extends MotionCommand<C,L,Integer>{

    private Integer angle;

    public LeftCommand(Turtle<C,L,Integer> turtle) {
        super(CommandName.LEFT, turtle);
        setFunctionSetParam(this::setAngleParameter);
        setFunctionGetParam((params) -> Stream.of(params).map(Integer::parseInt).findFirst());
        angle = null;
    }

    public LeftCommand(Turtle<C,L,Integer> turtle, int angle){
        this(turtle);
        this.angle = angle;
    }

    @Override
    public void execute() {
        getTurtle().rotate(angle);
    }


    /**
     * Sets the angle variation parameter for the new direction.
     *
     * @param angle the angle variation parameter for the new direction.
     */
    void setAngleParameter(int angle){
        this.angle = angle;
    }

    /**
     * Returns the direction parameter of this command.
     *
     * @return the direction parameter of this command.
     */
    int getAngleParameter(){
        return angle;
    }
}
