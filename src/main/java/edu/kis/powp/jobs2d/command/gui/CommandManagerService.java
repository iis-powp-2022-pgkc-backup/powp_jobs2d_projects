package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.io.CommandLoaderFactory;
import edu.kis.powp.jobs2d.command.io.ICommandLoader;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.panelcontroller.IPreviewPanelController;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class CommandManagerService implements ICommandManagerService {

	private final DriverManager driverManager = DriverFeature.getDriverManager();
	private final DriverCommandManager commandManager = CommandsFeature.getDriverCommandManager();
	private final IPreviewPanelController commandPreviewPanelController;


	public CommandManagerService(IPreviewPanelController commandPreviewPanelController) {
		this.commandPreviewPanelController = commandPreviewPanelController;
	}


	@Override
	public void runCommands() {
		DriverCommand command = commandManager.getCurrentCommand();
		command.execute(driverManager.getCurrentDriver());
	}

	@Override
	public void clearCommand() {
		commandManager.clearCurrentCommand();
		commandPreviewPanelController.updatePreviewPanel();
	}


	@Override
	public Publisher getPublisher() {
		return commandManager.getChangePublisher();
	}


	@Override
	public void loadCommands() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));

		int fileChooserState = fileChooser.showOpenDialog(null);

		if (fileChooserState == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String fileExtension = getFileExtension(selectedFile.getName());
			String data = null;

			try {
				data = new Scanner(selectedFile).useDelimiter("\\Z").next();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}

			ICommandLoader loader = new CommandLoaderFactory().getLoader(fileExtension);
			List<DriverCommand> complexCommands = loader.loadCommands(data);

			commandManager.setCurrentCommand(complexCommands, selectedFile.getName());
		}
	}

	@Override
	public String getCurrentCommandString() {
		return commandManager.getCurrentCommandString();
	}

	@Override
	public String observerListString() {
		StringBuilder observerListString = new StringBuilder();
		List<Subscriber> commandChangeSubscribers = this.commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString.append(observer.toString()).append(System.lineSeparator());
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = new StringBuilder("No observers loaded");

		return observerListString.toString();
	}


	@Override
	public IPreviewPanelController getCommandPreviewPanelController() {
		return commandPreviewPanelController;
	}

	private String getFileExtension(String selectedFile) {
		return selectedFile.substring(selectedFile.lastIndexOf(".") + 1);
	}
}
