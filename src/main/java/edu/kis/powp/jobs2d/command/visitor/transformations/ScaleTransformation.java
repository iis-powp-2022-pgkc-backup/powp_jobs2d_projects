package edu.kis.powp.jobs2d.command.visitor.transformations;

public class ScaleTransformation extends Transformation{
    private final double scaleX;
    private final double scaleY;

    public ScaleTransformation(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public void transform(int x, int y) {
        this.x = (int) (x * scaleX);
        this.y = (int) (y * scaleY);
    }
}
