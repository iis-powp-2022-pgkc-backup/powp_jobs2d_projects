package edu.kis.powp.jobs2d.command.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.line.BasicLine;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.file.IImportCommand;
import edu.kis.powp.jobs2d.command.file.ImporterFactory;
import edu.kis.powp.jobs2d.command.history.HistoryCommandList;
import edu.kis.powp.jobs2d.command.history.HistoryCommandObject;
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
		this.commandManager = commandManager;
		this.setTitle("History Manager");
		this.setSize(600, 1000);
		Container content = this.getContentPane();
		// Grid
		int numberOfHistoryCommandList = HistoryCommandList.getHistoryCommandList().getSize();
		content.setLayout(new GridLayout(numberOfHistoryCommandList+1,1));

		// TextArea
		JTextArea text = new JTextArea("History Manager");
		text.setFont(new Font("Arial Black", Font.BOLD, 24));
		content.add(text);
		updateHistory();

	}

	public void updateHistory()
	{
		Container content = this.getContentPane();
		// Buttons
		DefaultListModel <HistoryCommandObject> commands = HistoryCommandList.getHistoryCommandList();
		content.removeAll();
		for (int i = 0; i < commands.getSize(); i++) {
			HistoryCommandObject historyCommand = commands.get(i);
			JButton button = new JButton(historyCommand.toString());
			int finalI = i;
			button.addActionListener((ActionEvent e) -> this.selectEvent(finalI));
			content.add(button);
		}
	}


	public void selectEvent(int index) {
		DefaultListModel <HistoryCommandObject> commands = HistoryCommandList.getHistoryCommandList();
		HistoryCommandObject object = commands.get(index);
		DriverCommand command = object.getCommand();


		System.out.println("event");
		System.out.println(index);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateHistory();
		this.setVisible(!this.isVisible());
	}

}
