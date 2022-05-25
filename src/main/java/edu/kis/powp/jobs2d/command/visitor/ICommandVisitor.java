package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.transformers.TransformerCommand;

public interface ICommandVisitor {
    void visit(OperateToCommand operateToCommand);
    void visit(SetPositionCommand setPositionCommand);
    void visit(ICompoundCommand compoundCommand);
    void visit(TransformerCommand compoundCommand);
}
