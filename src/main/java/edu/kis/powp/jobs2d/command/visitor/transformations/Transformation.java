package edu.kis.powp.jobs2d.command.visitor.transformations;

public abstract class Transformation {
    protected int x = 0;
    protected int y = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void transform(int x, int y);
}
