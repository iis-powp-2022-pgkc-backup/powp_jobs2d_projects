package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.history.HistoryCommandList;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

public class SelectRunCurrentCommandOptionListener implements ActionListener {

	private DriverManager driverManager;

	public SelectRunCurrentCommandOptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Job2dDriver driver=driverManager.getCurrentDriver();
		DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
		command.execute(driver);

		String name = command.toString().split(" ")[0];
		HistoryCommandList.addCommandToList(name, command,driver);

	}
}