package edu.kis.powp.jobs2d.command.panelcontroller;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;

import javax.swing.*;

public class CommandPreviewPanelController implements IPreviewPanelController {

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

	@Override
	public void updatePreviewPanel() {
		DriverCommand currentCommand = commandManager.getCurrentCommand();
		previewPanelDrawerController.clearPanel();
		if (currentCommand != null) {
			currentCommand.execute(driver);
		}
	}

	@Override
	public JPanel getPreviewPanel() {
		return commandPreviewPanel;
	}
}
