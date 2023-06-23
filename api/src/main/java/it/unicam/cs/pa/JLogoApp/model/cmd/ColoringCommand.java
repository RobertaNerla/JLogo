package it.unicam.cs.pa.JLogoApp.model.cmd;


import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * This abstract class represents an abstract command that changes the color of a generic object.
 */
public abstract class ColoringCommand extends AbstractCommandWithName{

    private Color color;

    /**
     * Default constructor.
     */
    protected ColoringCommand(CommandName name, Color color) {
        super(name);
        this.color = color;
    }

    @Override
    public void setParamFromString(String[] strParams){
        String[] params = Arrays.copyOfRange(strParams, 1, strParams.length);
        try {
            List<Integer> rgbParam = Stream.of(params).map(Integer::parseInt).toList();
            Color color = new Color(rgbParam.get(0), rgbParam.get(1), rgbParam.get(2));
            setColorParameter(color);
        } catch(RuntimeException e){

        }
    }

    /**
     * Returns the current color parameter.
     *
     * @return the current color parameter.
     */
    public Color getColorParameter() {
        return color;
    }

    /**
     * Sets the color parameter for a command.
     *
     * @param color color
     */
    public void setColorParameter(Color color) {
        this.color = color;
    }
}
