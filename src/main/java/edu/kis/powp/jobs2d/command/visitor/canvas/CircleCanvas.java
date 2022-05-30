package edu.kis.powp.jobs2d.command.visitor.canvas;

public class CircleCanvas implements ICanvas {
	protected int radius;
	protected String name;

	public CircleCanvas(int radius, String name) {
		this.radius = radius;
		this.name = name;
	}

	public CircleCanvas(int radius) {
		this.radius = radius;
		this.name = "Circle canvas";
	}

	@Override
	public boolean isInBounds(int x, int y) {
		return x * x + y * y <= radius * radius;
	}

	@Override
	public String toString() {
		return name + " – ø" + radius;
	}
}
