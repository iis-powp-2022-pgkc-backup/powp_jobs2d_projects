package edu.kis.powp.jobs2d.command.visitor.canvas;

public class CustomCanvas extends Canvas {
	public CustomCanvas(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isInBounds(int x, int y) {
		return x >= -width / 2 && x <= width / 2 && y >= -height / 2 && y <= height / 2;
	}

	@Override
	public String toString() {
		return "Custom – " + width + "x" + height;
	}
}
