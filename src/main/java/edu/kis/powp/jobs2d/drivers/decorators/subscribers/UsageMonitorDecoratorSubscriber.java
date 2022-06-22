package edu.kis.powp.jobs2d.drivers.decorators.subscribers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.decorators.Job2dDriverUsageMonitorDecorator;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageMonitorDecoratorSubscriber implements Subscriber {
	private boolean useMonitor = false;

	public void changeMonitoringState() {
		useMonitor = !useMonitor;
	}

	@Override
	public void update() {
		Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
		if (!currentDriver.getClass().equals(Job2dDriverUsageMonitorDecorator.class) && useMonitor) {
			DriverFeature.getDriverManager().setCurrentDriver(new Job2dDriverUsageMonitorDecorator(currentDriver));
		}
	}
}
