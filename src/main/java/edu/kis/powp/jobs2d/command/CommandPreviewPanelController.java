package edu.kis.powp.jobs2d.command;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;

public class CommandPreviewPanelController {

	private JPanel commandPreviewPanel;
	private DriverCommandManager commandManager;
	private DrawPanelController previewPanelDrawerController;
	private Job2dDriver driver;

	public CommandPreviewPanelController(JPanel commandPreviewPanel, DriverCommandManager commandManager, Job2dDriver driver, DrawPanelController drawPanelController) {
		this.commandPreviewPanel = commandPreviewPanel;
		this.commandManager = commandManager;
		this.driver = driver;
		this.previewPanelDrawerController = drawPanelController;
	}

	public void updateCommandPreviewPanel() {
		DriverCommand currentCommand = commandManager.getCurrentCommand();
		previewPanelDrawerController.clearPanel();
		if (currentCommand != null) {
			currentCommand.execute(driver);
		}
	}

	public JPanel getCommandPreviewPanel() {
		return commandPreviewPanel;
	}
}
