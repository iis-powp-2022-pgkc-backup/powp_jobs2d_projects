package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.CommandLengthVisitor;
import edu.kis.powp.jobs2d.command.visitor.SurfaceCommandVisitor;
import edu.kis.powp.jobs2d.command.visitor.CountCommandVisitor;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class CommandDataObserver implements Subscriber {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	CountCommandVisitor countCommandVisitor = new CountCommandVisitor();
	SurfaceCommandVisitor surfaceCountCommandVisitor = new SurfaceCommandVisitor();
	CommandLengthVisitor lengthVisitor = new CommandLengthVisitor();

	public void update() {
		DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		command.accept(countCommandVisitor);
		command.accept(surfaceCountCommandVisitor);
		command.accept(lengthVisitor);


		logger.info("Command size: " + countCommandVisitor.getCountedCommands() +
				"\n   Command surface size: " + surfaceCountCommandVisitor.getCountedCommands() +
				"\n   Command length: " + lengthVisitor.getLength() +
				"\n   Command name: " + command);

	}

	public String toString() {
		return "Logger Command Change Observer";
	}

}
