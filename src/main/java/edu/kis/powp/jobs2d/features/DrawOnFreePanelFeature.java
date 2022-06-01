package edu.kis.powp.jobs2d.features;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class DrawOnFreePanelFeature {

	public static void setupButtonClick(Application application, DriverManager driverManager) {

		MouseInputAdapterForSetupButtonClick.enable(application.getFreePanel(), driverManager.getCurrentDriver());

	}
}
