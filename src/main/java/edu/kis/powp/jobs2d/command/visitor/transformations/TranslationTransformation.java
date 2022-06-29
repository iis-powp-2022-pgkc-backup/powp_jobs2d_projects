package edu.kis.powp.jobs2d.command.visitor.transformations;

public class TranslationTransformation extends Transformation{
    private final int deltaX;
    private final int deltaY;

    public TranslationTransformation(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void transform(int x, int y) {
        this.x = x + deltaX;
        this.y = y + deltaY;
    }
}
