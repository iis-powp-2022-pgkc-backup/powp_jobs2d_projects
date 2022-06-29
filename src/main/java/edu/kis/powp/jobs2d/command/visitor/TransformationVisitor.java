package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.command.visitor.transformations.Transformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransformationVisitor implements ICommandVisitor {
    private DriverCommand command = null;

    private Transformation transformation;

    public TransformationVisitor(Transformation transformation) {
        this.transformation = transformation;
    }


    @Override
    public void visit(OperateToCommand operateToCommand) {
        transformation.transform(operateToCommand.getPosX(), operateToCommand.getPosY());
        command = new OperateToCommand(transformation.getX(), transformation.getY());
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        transformation.transform(setPositionCommand.getPosX(), setPositionCommand.getPosY());
        command = new SetPositionCommand(transformation.getX(), transformation.getY());
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        List<DriverCommand> commandList = new ArrayList<>();

        for (Iterator<DriverCommand> it = compoundCommand.iterator(); it.hasNext(); ) {
            it.next().accept(this);
            commandList.add(command);
        }

        command = new ComplexCommand(commandList);
    }

    public DriverCommand getCommand() {
        return command;
    }

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }
}
