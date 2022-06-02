package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;

public interface IComplexCommandDeepCopyVisitor {
    void doForComplexCommand(ComplexCommand command);
}
