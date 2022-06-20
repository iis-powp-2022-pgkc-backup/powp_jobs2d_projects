package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.Iterator;

public class CommandLengthVisitor implements ICommandVisitor {
    private double length = 0;
    static int currentX = 0;
    static int currentY = 0;

    private double calcDistance(int fromX, int fromY, int toX, int toY) {
        return Math.sqrt((toY - fromY) * (toY - fromY) + (toX - fromX) * (toX - fromX));
    }

    @Override
    public void visit(OperateToCommand operateToCommand) {
        length = calcDistance(currentX, currentY, operateToCommand.getPosX(), operateToCommand.getPosY());
        currentX = operateToCommand.getPosX();
        currentY = operateToCommand.getPosY();
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand) {
        length = 0;
        currentX = setPositionCommand.getPosX();
        currentY = setPositionCommand.getPosY();
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        float totalSize = 0;
        for (Iterator<DriverCommand> it = compoundCommand.iterator(); it.hasNext();) {

            it.next().accept(this);
            totalSize += length;
        }
        length = totalSize;
    }

    public double getLength() {
        return length;
    }
}
