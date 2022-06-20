package edu.kis.powp.jobs2d.macros;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.ComplexCommandBuilder;
import edu.kis.powp.jobs2d.command.DriverCommand;

public abstract class DriverCallRecorderObservable {
    private List<DriverCallRecorderObserver> driverCallRecorderObservers = new ArrayList<>();
    private final static ComplexCommandBuilder builder = new ComplexCommandBuilder();

    public void addObserver(DriverCallRecorderObserver driverCallRecorderObserver) {
        driverCallRecorderObservers.add(driverCallRecorderObserver);
    }

    public void removeObserver(DriverCallRecorderObserver driverCallRecorderObserver) {
        driverCallRecorderObservers.remove(driverCallRecorderObserver);
    }

    public void addCall(DriverCommand driverCommand) {}

    public List<DriverCallRecorderObserver> getDriverCallRecorderObservers() {
        return driverCallRecorderObservers;
    }

    public static ComplexCommandBuilder getBuilder() {
        return builder;
    }
}
