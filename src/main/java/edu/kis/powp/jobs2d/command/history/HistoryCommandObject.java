package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public class HistoryCommandObject {
    private String name;
    private String date;
    private ICompoundCommand command;

    public HistoryCommandObject(String date, String name, ICompoundCommand command) {
        this.date = date;
        this.name = name;
        this.command = command;
    }

    public DriverCommand getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return date + " used " + name;
    }

}
