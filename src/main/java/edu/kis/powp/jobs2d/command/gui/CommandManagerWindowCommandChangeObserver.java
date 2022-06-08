package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindowCommandChangeObserver implements Subscriber {

	private final CommandManagerWindow commandManagerWindow;
	private final ICommandManagerService commandManagerService;

	public CommandManagerWindowCommandChangeObserver(CommandManagerWindow commandManagerWindow, ICommandManagerService commandManagerService) {
		super();
		this.commandManagerWindow = commandManagerWindow;
		this.commandManagerService = commandManagerService;
	}

	public String toString() {
		return "Current command change observer for command manager window";
	}

	@Override
	public void update() {
		commandManagerWindow.updateCurrentCommandField();
		commandManagerService.getCommandPreviewPanelController().updatePreviewPanel();
	}

}
