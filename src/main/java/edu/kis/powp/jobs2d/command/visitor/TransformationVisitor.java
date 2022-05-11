package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class TransformationVisitor implements ICommandVisitor{
	enum Transformation {Scale, Flip, Rotate};
	enum FlipDirection {Vertical, Horizontal}

	Transformation action = Transformation.Scale;
	double scaleX = 1;
	double scaleY = 1;
	FlipDirection flipDirection = FlipDirection.Horizontal;
	double rotationAngle = 0;

	@Override
	public void visit(OperateToCommand operateToCommand) {

	}

	@Override
	public void visit(SetPositionCommand setPositionCommand) {

	}

	@Override
	public void visit(ICompoundCommand compoundCommand) {
		switch(action) {
			case Scale:

				break;
			case Flip:
				break;
			case Rotate:
				break;
		}
	}
}
