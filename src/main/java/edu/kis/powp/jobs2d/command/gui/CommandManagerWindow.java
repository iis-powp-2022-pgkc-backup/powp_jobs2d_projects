package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.io.CommandLoaderFactory;
import edu.kis.powp.jobs2d.command.io.ICommandLoader;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.panelcontroller.IPreviewPanelController;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private final DriverManager driverManager = DriverFeature.getDriverManager();
	private final DriverCommandManager commandManager = CommandsFeature.getDriverCommandManager();
	private final IPreviewPanelController commandPreviewPanelController;
	private final JTextArea currentCommandField;

	private JTextArea observerListField;

	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(IPreviewPanelController commandPreviewPanelController) {
		this.setTitle("Command Manager");
		this.setSize(400, 500);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		content.add(currentCommandField, c);

		this.commandPreviewPanelController = commandPreviewPanelController;
		JPanel currentCommandPreviewPanel = this.commandPreviewPanelController.getPreviewPanel();
		content.add(currentCommandPreviewPanel, c);

		JButton btnRunCommands = new JButton("Run commands");
		btnRunCommands.addActionListener((ActionEvent e) -> this.runCommands());
		content.add(btnRunCommands, c);

		JButton btnLoadCommands = new JButton("Load commands");
		btnLoadCommands.addActionListener((ActionEvent e) -> this.loadCommands());
		content.add(btnLoadCommands, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
		content.add(btnClearObservers, c);
	}

	private void runCommands() {
		DriverCommand command = commandManager.getCurrentCommand();
		command.execute(driverManager.getCurrentDriver());
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		commandPreviewPanelController.updatePreviewPanel();

	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
		this.updateObserverListField();
	}

	public void loadCommands() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.json", "json"));

		int fileChooserState = fileChooser.showOpenDialog(this);

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

	private String getFileExtension(String selectedFile) {
		return selectedFile.substring(selectedFile.lastIndexOf(".") + 1);
	}


	private void updateObserverListField() {
		String observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}

	public IPreviewPanelController getCommandPreviewPanelController() {
		return commandPreviewPanelController;
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
