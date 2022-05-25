package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.visitor.ICommandVisitor;

public interface TransformerCommand {
	TransformedCoords execute(TransformedCoords coords);
	void accept(ICommandVisitor visitor);
}
