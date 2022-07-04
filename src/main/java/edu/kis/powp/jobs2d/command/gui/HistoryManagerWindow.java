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
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class HistoryManagerWindow extends JFrame implements WindowComponent {

	private DriverCommandManager commandManager;
	private JPanel buttonsPanel;

	public HistoryManagerWindow(DriverCommandManager commandManager) {
		this.commandManager = commandManager;
		this.setTitle("History Manager");
		this.setSize(500, 700);

		//title
		JPanel titlePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("History:");
		titlePane.add(label);
		//buttons
		buttonsPanel = new JPanel();
		buttonsPanel.setMaximumSize(new Dimension(this.getWidth(),this.getHeight()));
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));

		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.add(Box.createRigidArea(new Dimension(0,5)));
		listPane.add(buttonsPanel);
		listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(new JButton("Clear"));
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(new JButton("Exit"));



		Container content = getContentPane();
		content.add(titlePane, BorderLayout.BEFORE_FIRST_LINE);
		content.add(listPane, BorderLayout.CENTER);
		content.add(buttonPane, BorderLayout.PAGE_END);
		updateHistory();
	}

	private static void addAButton(String text, ActionListener action, Container container) {
		JButton button = new JButton(text);
		button.setMaximumSize(new Dimension(container.getMaximumSize().width,50));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}

	public void updateHistory()
	{
		DefaultListModel <HistoryCommandObject> commands = HistoryCommandList.getHistoryCommandList();
		buttonsPanel.removeAll();
		//
		for (int i = 0; i < commands.getSize(); i++) {
			HistoryCommandObject historyCommand = commands.get(i);
			final int finalI = i;
			addAButton(historyCommand.toString(),(ActionEvent e) -> this.selectEvent(finalI),buttonsPanel);
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
