package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * This abstract class is used to group all commands that deal with moving the turtle.
 *
 * @param <C> the type of generic coordinates system.
 * @param <L> the type of generic line.
 * @param <R> the type of generic argument function that moves the turtle.
 */
public abstract class MotionCommand<C extends Coordinate<C>, L extends Line<C>, R> extends AbstractCommandWithName{

    private final Turtle<C,L,R> turtle;
    /**
     * The function to use in order to set the parameters in the command.
     */
    private Consumer<R> functionSetParam;
    /**
     * The function to use in order to convert a string in parameters for the command.
     */
    private Function<String[],Optional<R>> functionGetParam;

    protected MotionCommand(CommandName name, Turtle<C,L,R> turtle){
        super(name);
        this.turtle= turtle;
    }

    protected MotionCommand(CommandName name, Turtle <C,L,R> turtle, Consumer<R> setParametersFunction, Function<String[],Optional<R>> getParamFunction) {
        super(name);
        this.turtle = turtle;
        this.functionSetParam = setParametersFunction;
        this.functionGetParam = getParamFunction;
    }

    protected C mover(C c, int dl) {
        C nextPos = turtle.getPosition().nextCoordinate(dl, turtle.getDirection());
        if (!turtle.getWorksheet().getBoundaryPredicate().test(nextPos)) {
            if (!turtle.getWorksheet().getWidthBoundaryPredicate().test(nextPos)) {
                nextPos = moveToXBound(turtle.getWorksheet().getWidth(), nextPos);
            }
            if (!turtle.getWorksheet().getHeightBoundaryPredicate().test(nextPos)) {
                nextPos = moveToYBound(turtle.getWorksheet().getHeight(), nextPos);
            }
        }
        return nextPos;
    }

    protected C moveToXBound(double width, C nextPos){
        return nextPos.nextCoordinateOnXBound(width);
    }

    protected C moveToYBound(double height, C nextPosition){
        return nextPosition.nextCoordinateOnYBound(height);
    }


    @Override
    public void setParamFromString(String[] strParams) {
        String[] params = Arrays.copyOfRange(strParams, 1, strParams.length);
        try{
            Optional<R> param= functionGetParam.apply(params);
            param.ifPresent(functionSetParam);
        } catch(RuntimeException e) {
        }
    }

    public Turtle<C, L, R> getTurtle() {
        return turtle;
    }

    protected void setFunctionSetParam(Consumer<R> functionSetParam) {
        this.functionSetParam = functionSetParam;
    }

    protected void setFunctionGetParam(Function<String[], Optional<R>> functionGetParam) {
        this.functionGetParam = functionGetParam;
    }
}
