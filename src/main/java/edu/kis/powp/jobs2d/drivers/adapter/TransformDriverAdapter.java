package edu.kis.powp.jobs2d.drivers.adapter;


import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;


public class TransformDriverAdapter extends LineDriverAdapter {

	public TransformDriverAdapter(DrawPanelController drawController, ILine line, String name) {
		super(drawController, line, name);
	}

	@Override
	public void operateTo(int x, int y) {
		line.setStartCoordinates(this.startX+100, this.startY);
		this.setPosition(x, y);
		line.setEndCoordinates(x+100, y);

		drawController.drawLine(line);
	}
}
