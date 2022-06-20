package edu.kis.powp.jobs2d.macros;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.ComplexCommandBuilder;
import edu.kis.powp.jobs2d.command.DriverCommand;

public class DriverCallRecorder implements DriverCallRecorderObserver {

    private ComplexCommandBuilder complexCommandBuilder;
    private boolean isRecording;

    public DriverCallRecorder() {
        complexCommandBuilder = ComplexCommandBuilder.builder();
        this.isRecording = false;
    }

    @Override
    public void recordDriverCall(Object call) {
        if (isRecording) {
            complexCommandBuilder = complexCommandBuilder.addCommand((DriverCommand) call);
            System.out.println(((DriverCommand) call).toString());
        }
    }

    public void startRecording() {
        isRecording = true;
    }

    public void stopRecording() {
        isRecording = false;
    }

    public ComplexCommand getComplexCommandRecording() {
        return complexCommandBuilder.build();
    }

    public void reset() {
        complexCommandBuilder = ComplexCommandBuilder.builder();
        isRecording = false;
    }
    
}
