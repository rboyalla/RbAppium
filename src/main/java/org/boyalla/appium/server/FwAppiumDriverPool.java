package org.boyalla.appium.server;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FwAppiumDriverPool {
	private ArrayList<FwAppiumDriver> allDrivers = new ArrayList<FwAppiumDriver>();
	private BlockingQueue<FwAppiumDriver> pool = new LinkedBlockingQueue<FwAppiumDriver>();
	private Thread checkDriverStatusThread;


	public FwAppiumDriverPool() {
		this.checkDriverStatusThread = new Thread() {
			public void run() {
				while (true) {
					System.out.println("checkDriverStatusThread");
					checkDiverStatus();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	private synchronized void runThread() {
		
		if (!this.checkDriverStatusThread.isAlive()) {
			this.checkDriverStatusThread.setDaemon(true);
			this.checkDriverStatusThread.start();
		}

	}

	public FwAppiumDriver getDriver() {
		runThread();
		FwAppiumDriver fwAppiumDriver = null;
		try {

			System.out.println("pool.take() before pool size: " + pool.size());
			fwAppiumDriver = pool.take();
			fwAppiumDriver.isFree = false;
			System.out.println("pool.take() after: " + fwAppiumDriver.getAppiumUrl());
			System.out.println("pool.take() after pool size: " + pool.size());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return fwAppiumDriver;

	}

	private synchronized void checkDiverStatus() {
		for (FwAppiumDriver fwAppiumDriver : allDrivers) {
			System.out.println("fwAppiumDriver.isFree: "+fwAppiumDriver.isFree);
			System.out.println("!pool.contains(fwAppiumDriver): "+!pool.contains(fwAppiumDriver));

			if (fwAppiumDriver.isFree && !pool.contains(fwAppiumDriver)) {
				pool.offer(fwAppiumDriver);
				System.out.println("offered : "+fwAppiumDriver.getAppiumUrl());
			}
			
		}
	}

	public synchronized void addDriver(FwAppiumDriver fwAppiumDriver) {
		if (!allDrivers.contains(fwAppiumDriver) && !pool.contains(fwAppiumDriver)) {
			fwAppiumDriver.isFree = true;
			allDrivers.add(fwAppiumDriver);

		}
		checkDiverStatus();

	}

	public void closePool() {
		for (FwAppiumDriver fwAppiumDriver : allDrivers) {
			fwAppiumDriver.quit();
			fwAppiumDriver.aService.stop();
		}
	}

	public void returnDiver(FwAppiumDriver driver) {
		driver.isFree=true;
	}
}
