package edu.kis.powp.jobs2d.command.visitor.canvas;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.ICommandVisitor;

import java.util.Iterator;

public class IsInBoundsVisitor implements ICommandVisitor {
	private boolean isInBounds = true;
	private ICanvas canvas;

	public IsInBoundsVisitor(ICanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void visit(OperateToCommand operateToCommand) {
		isInBounds = canvas.isInBounds(operateToCommand.getPosX(), operateToCommand.getPosY());
	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		isInBounds = canvas.isInBounds(setPositionCommand.getPosX(), setPositionCommand.getPosY());
	}

	@Override
	public void visit(ICompoundCommand compoundCommand) {
		Iterator<DriverCommand> iterator = compoundCommand.iterator();
		while (iterator.hasNext()) {
			DriverCommand driverCommand = iterator.next();
			driverCommand.accept(this);
			if (!isInBounds)
				return;
		}
	}

	public boolean getResult() {
		return isInBounds;
	}
}
