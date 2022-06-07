package edu.kis.powp.jobs2d.command.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.line.BasicLine;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.file.IImportCommand;
import edu.kis.powp.jobs2d.command.file.ImporterFactory;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class HistoryManagerWindow extends JFrame implements WindowComponent {

	private DriverCommandManager commandManager;

	public HistoryManagerWindow(DriverCommandManager commandManager) {
		// ?
		this.commandManager = commandManager;

		this.setTitle("History Manager");
		this.setSize(600, 1000);
		Container content = this.getContentPane();

		// Grid
		int numberOfHistoryCommandList = 5;
		content.setLayout(new GridLayout(numberOfHistoryCommandList+1,1));

		// TextArea
		JTextArea text = new JTextArea("History Manager");
		text.setFont(new Font("Arial Black", Font.BOLD, 24));
		content.add(text);

		// Buttons
		for (int i = 0; i < numberOfHistoryCommandList; i++) {
			JButton button = new JButton("Command x o godzinie xx:xx");
			button.addActionListener((ActionEvent e) -> this.selectEvent());
			content.add(button);
		}
		update();

	}

	public void selectEvent() {
//		todo delate
		this.update();
	}

	private void update() {
//		todo update
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		update();
		this.setVisible(!this.isVisible());
	}

}
