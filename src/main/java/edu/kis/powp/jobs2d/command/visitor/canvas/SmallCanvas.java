package edu.kis.powp.jobs2d.command.visitor.canvas;

public class SmallCanvas extends Canvas {
	public SmallCanvas() {
		super(50, 50);
	}

	@Override
	public boolean isInBounds(int x, int y) {
		return x >= -width / 2 && x <= width / 2 && y >= -height / 2 && y <= height / 2;
	}

	@Override
	public String toString() {
		return "Small – " + width + "x" + height;
	}
}
