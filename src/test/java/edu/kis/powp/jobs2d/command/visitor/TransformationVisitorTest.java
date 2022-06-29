package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.transformations.RotationTransformation;
import edu.kis.powp.jobs2d.command.visitor.transformations.ScaleTransformation;
import edu.kis.powp.jobs2d.command.visitor.transformations.TranslationTransformation;

import java.util.Arrays;
import java.util.Iterator;

public class TransformationVisitorTest {
    public static void main(String[] args) {
        TransformationVisitor scaleVisitor = new TransformationVisitor(new ScaleTransformation(2, 3));
        TransformationVisitor rotationVisitor = new TransformationVisitor(new RotationTransformation(90));
        TransformationVisitor translationVisitor = new TransformationVisitor(new TranslationTransformation(2, 3));

        OperateToCommand operateToCommand = new OperateToCommand(10, 10);
        SetPositionCommand setPositionCommand = new SetPositionCommand(10, 0);
        ComplexCommand complexCommand = new ComplexCommand(Arrays.asList(operateToCommand, setPositionCommand));

        operateToCommand.accept(scaleVisitor);
        OperateToCommand scaled = (OperateToCommand) scaleVisitor.getCommand();
        assert (scaled.getPosX() == 20) : "scaled position should be 20 and is " + scaled.getPosX();
        assert (scaled.getPosY() == 30) : "scaled position should be 30 and is " + scaled.getPosY();

        setPositionCommand.accept(rotationVisitor);
        SetPositionCommand rotated = (SetPositionCommand) rotationVisitor.getCommand();
        assert (rotated.getPosX() == 0) : "Rotated position should be 0 but is " + rotated.getPosX();
        assert (rotated.getPosY() == 10) : "Rotated position should be 10 but is " + rotated.getPosY();

        complexCommand.accept(translationVisitor);
        ComplexCommand translated = (ComplexCommand) translationVisitor.getCommand();
        Iterator<DriverCommand> iterator = translated.iterator();
        DriverCommand driverCommand = iterator.next();
        assert driverCommand.getClass() == OperateToCommand.class;
        assert ((OperateToCommand) driverCommand).getPosX() == 12;
        assert ((OperateToCommand) driverCommand).getPosY() == 13;
        driverCommand = iterator.next();
        assert driverCommand.getClass() == SetPositionCommand.class;
        assert ((SetPositionCommand) driverCommand).getPosX() == 12;
        assert ((SetPositionCommand) driverCommand).getPosY() == 3;
    }
}
