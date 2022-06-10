package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.Iterator;

public class ComplexCommandDeepCopyVisitor implements IDriverCommandsVisitor {
    private ComplexCommand deepCopyOfComplexCommand = null;

    @Override
    public void doForOperateToCommand(OperateToCommand command) {
        if(deepCopyOfComplexCommand != null)
            deepCopyOfComplexCommand.appendCommand(command.driverCommandClone());
    }

    @Override
    public void doForSetPositionCommand(SetPositionCommand command) {
        if(deepCopyOfComplexCommand != null)
            deepCopyOfComplexCommand.appendCommand(command.driverCommandClone());
    }

    @Override
    public void doForCompoundCommand(ICompoundCommand command) {
        if(deepCopyOfComplexCommand == null) {
            deepCopyOfComplexCommand = new ComplexCommand();
            Iterator<DriverCommand> iterator = command.iterator();
            while (iterator.hasNext()) {
                iterator.next().accept(this);
            }
        }
        else {
            ComplexCommandDeepCopyVisitor visitor = new ComplexCommandDeepCopyVisitor();
            command.accept(visitor);
            deepCopyOfComplexCommand.appendCommand(visitor.getDeepCopyOfComplexCommand());
        }
    }

    public ComplexCommand getDeepCopyOfComplexCommand() {
        return deepCopyOfComplexCommand;
    }

}
