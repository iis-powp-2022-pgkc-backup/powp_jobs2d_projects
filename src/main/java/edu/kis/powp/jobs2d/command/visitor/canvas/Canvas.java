package edu.kis.powp.jobs2d.command.visitor.canvas;

public abstract class Canvas {
	protected int width;
	protected int height;

	public Canvas(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public abstract boolean isInBounds(int x, int y);
}
