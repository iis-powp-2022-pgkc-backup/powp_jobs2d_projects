package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommandDeepCopyVisitor implements IDriverCommandsVisitor {
    private DriverCommand deepCopyOfComplexCommand = null;

    @Override
    public void doForOperateToCommand(OperateToCommand command) {
            deepCopyOfComplexCommand = command.driverCommandClone();
    }

    @Override
    public void doForSetPositionCommand(SetPositionCommand command) {
            deepCopyOfComplexCommand = command.driverCommandClone();
    }

    @Override
    public void doForCompoundCommand(ICompoundCommand command) {
        List<DriverCommand> commandList = new ArrayList<>();
        Iterator<DriverCommand> iterator = command.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(this);
            commandList.add(deepCopyOfComplexCommand);
        }
        deepCopyOfComplexCommand = new ComplexCommand(commandList);
    }

    public DriverCommand getDeepCopyOfComplexCommand() {
        return deepCopyOfComplexCommand;
    }

}
