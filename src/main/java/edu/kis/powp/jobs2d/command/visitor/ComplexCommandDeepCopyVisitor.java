package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;

public class ComplexCommandDeepCopyVisitor implements IComplexCommandDeepCopyVisitor {
    private ComplexCommand deepCopyOfComplexCommand = null;

    @Override
    public void doForComplexCommand(ComplexCommand command) {
        deepCopyOfComplexCommand = new ComplexCommand();
        for(DriverCommand driverCommand : command.getCommandList()) {
            DriverCommand tempDriverCommand = driverCommand.driverCommandClone();
            deepCopyOfComplexCommand.appendCommand(tempDriverCommand);
        }
    }

    public ComplexCommand getDeepCopyOfComplexCommand() {
        return deepCopyOfComplexCommand;
    }
}
