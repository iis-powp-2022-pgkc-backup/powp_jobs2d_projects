package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.CommandDataObserver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature {

	private static DriverCommandManager commandManager;

	public static void setupCommandManager() {
		commandManager = new DriverCommandManager();

		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);

		CommandDataObserver dataObserver = new CommandDataObserver();
		commandManager.getChangePublisher().addSubscriber(dataObserver);
	}

	/**
	 * Get manager of application driver command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static DriverCommandManager getDriverCommandManager() {
		return commandManager;
	}
}
