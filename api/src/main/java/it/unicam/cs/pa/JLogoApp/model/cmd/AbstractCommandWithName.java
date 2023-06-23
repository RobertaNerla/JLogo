package it.unicam.cs.pa.JLogoApp.model.cmd;

public abstract class AbstractCommandWithName implements Command{

    private final CommandName name;

    protected AbstractCommandWithName(CommandName name) {
        this.name = name;
    }

    @Override
    public CommandName getNameCommand(){
        return this.name;
    };
}
