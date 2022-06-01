package edu.kis.powp.jobs2d.drivers.adapter;


import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;


public class TransformDriverAdapter implements Job2dDriver {

	protected ILine line;
	protected int startX = 0, startY = 0;
	protected String name;

	protected DrawPanelController drawController;
	protected LineDriverAdapter lineDriverAdapter;

	public TransformDriverAdapter(DrawPanelController drawController, ILine line, String name) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;
		this.lineDriverAdapter = new LineDriverAdapter(this.drawController, this.line, this.name);
	}
	@Override
	public void operateTo(int x, int y) {
		// line.setStartCoordinates(this.startX+100, this.startY);
		// this.setPosition(x, y);
		// line.setEndCoordinates(x+100, y);

		// drawController.drawLine(line);
	}

	@Override
	public void setPosition(int x, int y) {
		this.startX = x;
		this.startY = y;
	}
}
