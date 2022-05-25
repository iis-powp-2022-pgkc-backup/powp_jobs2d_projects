package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.transformers.TransformerCommand;

import java.util.Iterator;

public class CountCommandVisitor implements ICommandVisitor {
    private int countedCommands = 0;

    @Override
    public void visit(OperateToCommand operateToCommand) {
        countedCommands = 1;
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        countedCommands = 1;
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        int counter = 0;
        for (Iterator<DriverCommand> it = compoundCommand.iterator(); it.hasNext(); ) {
            it.next().accept(this);
            counter += countedCommands;
        }
        countedCommands = counter;
    }

    @Override
    public void visit(TransformerCommand compoundCommand) {
        countedCommands = 1;
    }

    public int getCountedCommands() {
        return countedCommands;
    }
}
