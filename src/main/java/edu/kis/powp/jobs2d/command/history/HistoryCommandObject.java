package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class HistoryCommandObject {
    private String name;
    private String date;
    private DriverCommand command;
    private Job2dDriver driver;

    public HistoryCommandObject(String date, String name, DriverCommand command,Job2dDriver driver) {
        this.date = date;
        this.name = name;
        this.command = command;
        this.driver=driver;
    }

    public DriverCommand getCommand() {
        return command;
    }

    public void execute(){
        switch(this.name)
        {
            case "FigureJoe1":
                FiguresJoe.figureScript1(driver);
                break;
            case "FigureJoe2":
                FiguresJoe.figureScript2(driver);
                break;
            default:
                command.execute(driver);
        }
    }

    @Override
    public String toString() {
        return date + " used " + name;
    }

}
