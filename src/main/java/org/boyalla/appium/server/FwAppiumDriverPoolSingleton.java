package org.boyalla.appium.server;

public class FwAppiumDriverPoolSingleton {
	static FwAppiumDriverPool appiumDriverPool=new FwAppiumDriverPool();

	public synchronized static FwAppiumDriverPool getPool( ) {
		return appiumDriverPool;
	}
}
