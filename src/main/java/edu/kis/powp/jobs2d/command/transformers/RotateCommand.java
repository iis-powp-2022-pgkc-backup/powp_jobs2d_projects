package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.visitor.ICommandVisitor;

public class RotateCommand implements TransformerCommand {
	private final double angle;

	public RotateCommand(double angle) {
		this.angle = angle;
	}

	@Override
	public TransformedCoords execute(TransformedCoords coords) {
		return coords.rotate(angle);
	}

	@Override
	public void accept(ICommandVisitor visitor) {
		visitor.visit( this);
	}
}
