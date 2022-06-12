package edu.kis.powp.jobs2d.drivers.adapter;

public class MoveRightTransformation implements Transformation{

    private int scale;
    public MoveRightTransformation(int scale)
    {
        this.scale = scale;
    }
    @Override
    public int[] transform(int x, int y) {
        x += scale;
        int[] retval = {x, y};
        return retval;
        
    }
    
}
