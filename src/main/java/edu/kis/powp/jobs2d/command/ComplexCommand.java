package edu.kis.powp.jobs2d.command;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {
    private final List<DriverCommand> commandList;

    public ComplexCommand(List<DriverCommand> commandList) {
        this.commandList = commandList;
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand command : this.commandList) {
            command.execute(driver);
        }
    }
    @Override
    public Iterator<DriverCommand> iterator() {
        return commandList.iterator();
    }

    @Override
    protected ComplexCommand clone() throws CloneNotSupportedException {
        return (ComplexCommand) super.clone();
    }

    @Override
    public Object copyDriverCommand() throws CloneNotSupportedException {
        return ICompoundCommand.super.copyDriverCommand();
    }

    public ComplexCommand copy() throws CloneNotSupportedException {
        List<DriverCommand> newCommandList=new ArrayList<>();
        for(DriverCommand driverCommand: this.commandList){
            newCommandList.add((DriverCommand) driverCommand.copyDriverCommand());
        }
        return new ComplexCommand(newCommandList);
    }
}
