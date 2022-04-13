package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Interface extending Job2dDriverCommand to execute more than one command.
 */
public interface ICompoundCommand extends DriverCommand {

	public Iterator<DriverCommand> iterator();

	@Override
	default Object copyDriverCommand() throws CloneNotSupportedException {
		return DriverCommand.super.copyDriverCommand();
	}
}
