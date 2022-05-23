package edu.kis.powp.jobs2d.command.visitor.canvas;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.ICommandVisitor;

public class IsInBoundsVisitor implements ICommandVisitor {
	private boolean isInBounds;
	private Canvas canvas;

	public IsInBoundsVisitor(Canvas canvas) {
		this.canvas = canvas;
	}

	// Trzeba jakoś zareagować na wszystkie możliwe do wykonania komendy.
	// Trzeba sprawdzić, czy po wykonaniu komendy głowica nie wyszła poza mapę.
	@Override
	public void visit(OperateToCommand operateToCommand) {

	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {

	}

	@Override
	public void visit(ICompoundCommand compoundCommand) {

	}

	public boolean getResult(int x, int y) {
		return isInBounds;
	}
}
