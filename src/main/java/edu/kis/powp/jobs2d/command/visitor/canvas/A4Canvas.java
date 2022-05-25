package edu.kis.powp.jobs2d.command.visitor.canvas;

public class A4Canvas extends Canvas {
	public A4Canvas() {
		super(210, 297);
	}

	@Override
	public boolean isInBounds(int x, int y) {
		return x >= -width / 2 && x <= width / 2 && y >= -height / 2 && y <= height / 2;
	}

	@Override
	public String toString() {
		return "A4 – " + width + "x" + height;
	}
}
