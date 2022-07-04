package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import javax.swing.*;
import java.sql.Driver;
import java.time.format.DateTimeFormatter;

public class HistoryCommandList {

    private static DefaultListModel <HistoryCommandObject> historyCommandList = new DefaultListModel<>();

    public static void addCommandToList(String commandName, DriverCommand command, Job2dDriver driver) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        HistoryCommandObject localHistoryCommandObject = new HistoryCommandObject(java.time.LocalTime.now().format(formatter), commandName, command, driver);
        historyCommandList.addElement(localHistoryCommandObject);
    }

    public static void Clear()
    {
        historyCommandList.removeAllElements();
    }

    public static DefaultListModel <HistoryCommandObject> getHistoryCommandList() {
        return historyCommandList;
    }

}
