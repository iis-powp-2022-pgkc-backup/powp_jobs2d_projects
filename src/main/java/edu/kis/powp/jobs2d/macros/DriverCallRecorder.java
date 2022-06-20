package edu.kis.powp.jobs2d.macros;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;

public class DriverCallRecorder implements DriverCallRecorderObserver {

    private ComplexCommand complexCommand = new ComplexCommand(new ArrayList<DriverCommand>());

    @Override
    public void recordDriverCall(Object call) {
        // complexCommand.add((DriverCommand) call);
        System.out.println(((DriverCommand) call).getX() + " " + ((DriverCommand) call).getY());
    }
    
}
