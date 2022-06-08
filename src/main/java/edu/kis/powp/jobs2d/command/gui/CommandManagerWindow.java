package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.observer.Publisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private final JTextArea currentCommandField;
	private final JTextArea observerListField;


	private final ICommandManagerService commandManagerService;

	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(ICommandManagerService commandManagerService) {
		this.commandManagerService = commandManagerService;

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

		JPanel currentCommandPreviewPanel = this.commandManagerService.getCommandPreviewPanelController().getPreviewPanel();
		content.add(currentCommandPreviewPanel, c);

		JButton btnRunCommands = new JButton("Run commands");
		btnRunCommands.addActionListener(
				(ActionEvent e) -> this.commandManagerService.runCommands()
		);
		content.add(btnRunCommands, c);

		JButton btnLoadCommands = new JButton("Load commands");
		btnLoadCommands.addActionListener(
				(ActionEvent e) -> this.commandManagerService.loadCommands()
		);
		content.add(btnLoadCommands, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener(
				(ActionEvent e) -> this.commandManagerService.clearCommand()
		);
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener(
				(ActionEvent e) -> {

					Publisher p = this.commandManagerService.getPublisher();
					p.clearObservers();
					CommandManagerWindowCommandChangeObserver commandManagerWindowCommandChangeObserver =
							new CommandManagerWindowCommandChangeObserver(this, commandManagerService);
					p.addSubscriber(commandManagerWindowCommandChangeObserver);

					this.updateObserverListField();
				}
		);
		content.add(btnClearObservers, c);
	}


	public void updateCurrentCommandField() {
		currentCommandField.setText(
				this.commandManagerService.getCurrentCommandString()
		);
	}


	private void updateObserverListField() {
		observerListField.setText(
				this.commandManagerService.observerListString()
		);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		this.setVisible(!this.isVisible());
	}

}
