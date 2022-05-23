package edu.kis.powp.jobs2d.command.visitor.canvas;

public class A4Canvas extends Canvas {
	public A4Canvas() {
		super(210, 297);
	}

	@Override
	public boolean isInBounds(int x, int y) {
		return x >= 0 && x <= width && y >= 0 && y <= height;
	}
}
