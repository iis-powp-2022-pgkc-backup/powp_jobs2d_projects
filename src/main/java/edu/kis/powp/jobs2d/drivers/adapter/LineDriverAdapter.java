package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.macros.DriverCallRecorder;
import edu.kis.powp.jobs2d.macros.DriverCallRecorderObservable;
import edu.kis.powp.jobs2d.macros.DriverCallRecorderObserver;

/**
 * Line adapter - Job2dDriver with DrawPanelController object.
 */
public class LineDriverAdapter extends DriverCallRecorderObservable implements Job2dDriver {
	private ILine line;
	private int startX = 0, startY = 0;
	private String name;

	private DrawPanelController drawController;

	public LineDriverAdapter(DrawPanelController drawController, ILine line, String name, DriverCallRecorder recorder) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;

		addObserver(recorder);
	}

	@Override
	public void setPosition(int x, int y) {
		this.startX = x;
		this.startY = y;

		addCall(new SetPositionCommand(x, y));
	}

	@Override
	public void operateTo(int x, int y) {
		line.setStartCoordinates(this.startX, this.startY);
		this.setPosition(x, y);
		line.setEndCoordinates(x, y);

		drawController.drawLine(line);

		addCall(new OperateToCommand(x, y));
	}

	@Override
	public String toString() {
		return "2d device simulator - " + name;
	}

	@Override
	public void addCall(DriverCommand driverCommand) {
		for (DriverCallRecorderObserver observer : getDriverCallRecorderObservers()) {
			observer.recordDriverCall(driverCommand);
		}
	}
}
