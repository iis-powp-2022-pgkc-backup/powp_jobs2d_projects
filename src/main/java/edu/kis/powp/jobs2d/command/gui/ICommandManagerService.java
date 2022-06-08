package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.panelcontroller.IPreviewPanelController;
import edu.kis.powp.observer.Publisher;

public interface ICommandManagerService {
	void runCommands();

	void clearCommand();

	Publisher getPublisher();

	void loadCommands();

	String getCurrentCommandString();

	String observerListString();

	IPreviewPanelController getCommandPreviewPanelController();
}
