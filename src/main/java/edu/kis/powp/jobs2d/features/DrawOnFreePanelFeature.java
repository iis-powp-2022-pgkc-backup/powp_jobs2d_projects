package edu.kis.powp.jobs2d.features;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class DrawOnFreePanelFeature {

	private static Application app;
	private static DriverManager driverMan;

	public static void setupButtonClick(Application application, DriverManager driverManager) {
		app = application;
		driverMan = driverManager;

		app.getFreePanel().addMouseListener(new MouseInputAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				Point clickPoint = e.getPoint();
				clickPoint.x -= app.getFreePanel().getSize().width/2;
				clickPoint.y -= app.getFreePanel().getSize().height/2;
				if (e.getButton() == MouseEvent.BUTTON1) {
					driverMan.getCurrentDriver().operateTo(clickPoint.x, clickPoint.y);
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					driverMan.getCurrentDriver().setPosition(clickPoint.x, clickPoint.y);
				}
			}
		});
	}
}
