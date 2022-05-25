package edu.kis.powp.jobs2d.command.visitor.canvas;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.ICommandVisitor;

import java.util.Iterator;

public class IsInBoundsVisitor implements ICommandVisitor {
	private boolean isInBounds = true;
	private Canvas canvas;

	public IsInBoundsVisitor(Canvas canvas) {
		this.canvas = canvas;
	}

	// Trzeba jakoś zareagować na wszystkie możliwe do wykonania komendy.
	// Trzeba sprawdzić, czy po wykonaniu komendy głowica nie wyszła poza mapę.
	@Override
	public void visit(OperateToCommand operateToCommand) {
		if(!canvas.isInBounds(operateToCommand.getPosX(),operateToCommand.getPosY())) {
			isInBounds=false;
		}

	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {
		if(!canvas.isInBounds(setPositionCommand.getPosX(),setPositionCommand.getPosY())) {
			isInBounds=false;
		}
	}

	@Override
	public void visit(ICompoundCommand compoundCommand) {
		Iterator<DriverCommand> iterator = compoundCommand.iterator();
		while (iterator.hasNext()) {
			DriverCommand driverCommand = iterator.next();
			driverCommand.accept(this);
		}
	}

	public boolean getResult() {
		return isInBounds;
	}
}
