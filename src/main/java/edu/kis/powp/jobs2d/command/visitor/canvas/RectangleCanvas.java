package edu.kis.powp.jobs2d.command.visitor.canvas;

public class RectangleCanvas implements ICanvas {
	protected int width;
	protected int height;
	protected String name;

	public RectangleCanvas(int width, int height, String name) {
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public RectangleCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		this.name = "Rectangle canvas";
	}

	public boolean isInBounds(int x, int y) {
		return x >= -width / 2 && x <= width / 2 && y >= -height / 2 && y <= height / 2;
	}

	@Override
	public String toString() {
		return name + " – " + width + "x" + height;
	}
}
