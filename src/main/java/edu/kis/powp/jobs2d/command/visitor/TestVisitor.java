package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestVisitor implements IVisitor {
	private final Logger logger;
	private int operateToCommandCount = 0;
	private int setPositionCommandCount = 0;
	private int compundCommandCount = 0;

	public TestVisitor(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void doForOperateToCommand(OperateToCommand command) {
		operateToCommandCount++;
	}

	@Override
	public void doForSetPositionCommand(SetPositionCommand command) {
		setPositionCommandCount++;
	}

	@Override
	public void doForCompoundCommand(ICompoundCommand command) {
		compundCommandCount++;
		Iterator<DriverCommand> iterator = command.iterator();
		while(iterator.hasNext()) {
			iterator.next().accept(this);
		}
	}

	public void logResults() {
		logger.log(Level.INFO, "Operate To Commands count: " + operateToCommandCount);
		logger.log(Level.INFO, "Set Position Commands count: " + setPositionCommandCount);
		logger.log(Level.INFO, "Compound Commands count: " + compundCommandCount);
	}
}
