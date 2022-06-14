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
	protected Transformation transformation;

	public TransformDriverAdapter(DrawPanelController drawController, ILine line, String name, Transformation transformation) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;
		this.lineDriverAdapter = new LineDriverAdapter(this.drawController, this.line, this.name);
		this.transformation = transformation;
	}

	@Override
	public void operateTo(int x, int y) {
		int[] new_coords = this.transformation.transform(this.startX, this.startY);
		line.setStartCoordinates(new_coords[0], new_coords[1]);
		this.setPosition(x, y); // TODO jak coś się posypie, to tutaj...
		new_coords = this.transformation.transform(x, y);
		line.setEndCoordinates(new_coords[0], new_coords[1]);

		drawController.drawLine(line);
	}

	@Override
	public void setPosition(int x, int y) {
		this.startX = x;
		this.startY = y;
	}
}
