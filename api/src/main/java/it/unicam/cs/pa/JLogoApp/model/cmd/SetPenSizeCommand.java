package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Turtle;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * This command is used to set the pen size of a generic Logo turtle.
 * @param <C> type for a generic coordinate system.
 */
public class SetPenSizeCommand<C extends Coordinate<C>,L extends Line<C>, R> extends AbstractCommandWithName{

    private final Turtle<C,L,R> turtle;
    int size;

    public SetPenSizeCommand(Turtle<C,L,R> turtle) {
        super(CommandName.SETPENSIZE);
        this.turtle = turtle;
        size = turtle.getPenSize();
    }

    public SetPenSizeCommand(Turtle<C,L,R> turtle, int size){
        this(turtle);
        this.size = size;
    }

    @Override
    public void execute() {
        if (isValidPenSize()){
            turtle.setPenSize(size);
        } else {
            turtle.setPenSize(1);
        }

    }

    @Override
    public void setParamFromString(String[] strParams) {
        String[] params = Arrays.copyOfRange(strParams, 1, strParams.length);
        try{
            Optional<Integer> param= Stream.of(params).map(Integer::parseInt).findFirst();
            param.ifPresent(this::setPenSizeParameter);
        } catch(RuntimeException e) {
        }

    }

    /**
     * Sets the pen size parameter of this command.
     * @param size the pen size.
     */
    void setPenSizeParameter(int size){
        this.size = size;
    }

    /**
     * Returns the pen size parameter of this command.
     * @return the pen size parameter of this command.
     */
    int getPenSizeParameter(){
        return size;
    }

    private boolean isValidPenSize(){
        return size >= 1;
    }
}
