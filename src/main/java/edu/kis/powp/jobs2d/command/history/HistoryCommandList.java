package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import javax.swing.*;
import java.sql.Driver;
import java.time.format.DateTimeFormatter;

public class HistoryCommandList {

    private static DefaultListModel <HistoryCommandObject> historyCommandList = new DefaultListModel<>();

    public static void addCommandToList(String commandName, DriverCommand command) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        HistoryCommandObject localHistoryCommandObject = new HistoryCommandObject(java.time.LocalTime.now().format(formatter), commandName, command);
        historyCommandList.addElement(localHistoryCommandObject);
    }

    public static DefaultListModel <HistoryCommandObject> getHistoryCommandList() {
        return historyCommandList;
    }

}
