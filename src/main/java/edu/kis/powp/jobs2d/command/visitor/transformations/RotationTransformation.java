package edu.kis.powp.jobs2d.command.visitor.transformations;

public class RotationTransformation extends Transformation{
    private final double angle;

    public RotationTransformation(double angle) {
        this.angle = angle;
    }

    @Override
    public void transform(int x, int y) {
        double cos = Math.cos(Math.toRadians(angle));
        double sin = Math.sin(Math.toRadians(angle));
        this.x = (int) (x * cos - y * sin);
        this.y = (int) (x * sin + y * cos);
    }
}
