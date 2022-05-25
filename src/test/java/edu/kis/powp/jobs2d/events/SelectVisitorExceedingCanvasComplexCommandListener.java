package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.canvas.Canvas;
import edu.kis.powp.jobs2d.command.visitor.canvas.IsInBoundsVisitor;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectVisitorExceedingCanvasComplexCommandListener implements ActionListener {
    private Logger logger = Logger.getLogger("global");
    private DriverManager driverManager;
    private Canvas canvas;

    public SelectVisitorExceedingCanvasComplexCommandListener(DriverManager driverManager, Canvas canvas) {
        this.driverManager = driverManager;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand driverCommand = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if (driverCommand == null) {
            logger.info("No command loaded!");
        } else {
            IsInBoundsVisitor isInBoundsVisitor = new IsInBoundsVisitor(canvas);
            driverCommand.accept(isInBoundsVisitor);
            if (isInBoundsVisitor.getResult()) {
                logger.info("Is in bounds: " + canvas.toString());
            } else {
                logger.info("Is not in bounds: " + canvas.toString());
            }
        }
    }
}
