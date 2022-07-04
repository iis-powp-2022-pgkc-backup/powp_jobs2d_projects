package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.history.HistoryCommandList;
import edu.kis.powp.jobs2d.command.history.HistoryCommandObject;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		buttonsPanel.setBackground(Color.white);
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
		//clear button
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener((ActionEvent e)->clearHistory());
		buttonPane.add(clearButton);

		Container content = getContentPane();
		content.add(titlePane, BorderLayout.BEFORE_FIRST_LINE);
		content.add(listPane, BorderLayout.CENTER);
		content.add(buttonPane, BorderLayout.PAGE_END);
		updateHistory();
	}

	private static void addAButton(String text, ActionListener action, Container container) {
		JButton button = new JButton(text);
		button.addActionListener(action);
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


	public static void selectEvent(int index) {
		DefaultListModel <HistoryCommandObject> commands = HistoryCommandList.getHistoryCommandList();

		DrawerFeature.getDrawerController().clearPanel();
		for (int i = 0; i <= index; i++)
			commands.get(i).execute();

	}

	public void clearHistory()
	{
		HistoryCommandList.Clear();
		updateHistory();
		this.repaint();
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateHistory();
		this.setVisible(!this.isVisible());
	}

}
