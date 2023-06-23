package it.unicam.cs.pa.JLogoApp.model.cmd;

import it.unicam.cs.pa.JLogoApp.SimpleController;
import it.unicam.cs.pa.JLogoApp.io.CommandReader;
import it.unicam.cs.pa.JLogoApp.io.CommandsLoader;
import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Line;


import java.io.IOException;
import java.util.Arrays;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * This command is used to execute a list of commands n-times.
 */
public class RepeatCommand<C extends Coordinate<C>, L extends Line<C>> extends AbstractCommandWithName {

    public final CommandsLoader<Command> loaderCommandsToRepeat = this::parse;
    public static final CommandReader<Command> lineParser = Command::fromStringCommand;

    private final SimpleController<C,L> controller;
    private String commandsListString = "";
    private int rep;


    public RepeatCommand(SimpleController<C,L> controller){
        super(CommandName.REPEAT);
        this.controller = controller;
        this.rep = 1;

    }

    public RepeatCommand(CommandName name, SimpleController<C,L> controller, int rep) {
        super(name);
        this.controller = controller;
        this.rep = rep;
    }

    /**
     * Defines a new function to parse the string of commands and add them to the list to execute.
     * @param content string that contains a list of commands.
     * @param commands the list of commands to execute.
     */
    public void parse(String content, List<Command> commands){
        int i = 0;
        while(i<rep){
            String[] lines = content.split(";");
            for (String line : lines) {
                try{
                    Command command = lineParser.parse(line.trim(),commands);
                    command.execute();
                }catch(IOException e){
                }
            } i++;
        }
    }

    @Override
    public void execute() {
        try{
            loaderCommandsToRepeat.parse(
                    commandsListString, controller.availableCommands(controller.getCurrentTurtle()));
        }catch (IOException e){

        }

    }



    @Override
    public void setParamFromString(String[] strParams) {
        String[] params = Arrays.copyOfRange(strParams, 1, strParams.length);
        try{
            Optional<Integer> param = Stream.of(params).map(Integer::parseInt).findFirst();
            param.ifPresent(this::setRepParameter);
            String strCmdList = Arrays.toString(Arrays.copyOfRange(strParams, 2, strParams.length));
            setCommandsListStringParameter(strCmdList);

            } catch(RuntimeException e){
        }
    }

    /**
     * Sets the list of commands to repeat.
     * @param commandsListString commands to repeat in string format.
     */
    public void setCommandsListStringParameter(String commandsListString) {

        commandsListString = commandsListString.replaceAll("[\\[\\](){}]", "");
        commandsListString = commandsListString.replace(",", "");
        this.commandsListString = commandsListString;
    }

    /**
     * Returns the number of repeat parameter.
     *
     * @return the number of repeat parameter.
     */
    public int getRepParameter() {
        return rep;
    }

    /**
     * Sets the value parameter to specify how many times a list of commands is repeated.
     */
    public void setRepParameter(int rep) {
        this.rep = rep;
    }

    /**
     * Returns the string of commands to repeat.
     *
     * @return the string of commands to repeat.
     */
    public String getCommandsListString() {
        return commandsListString;
    }


}
