package org.boyalla.appium.server;

import java.io.File;
import java.net.URL;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumIOSServer {

	public static void main(String[] args) throws InterruptedException {
		startAppium();

	}

	public static AppiumDriverLocalService getAppium() {
		return null;

	}

	public static void startAppium() throws InterruptedException {
		/*
		 * AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new
		 * AppiumServiceBuilder() .usingDriverExecutable(new
		 * File("/Applications/Appium.app/Contents/Resources/node/bin/node"))
		 * .withAppiumJS(new File(
		 * "/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js"
		 * )) .withIPAddress("127.0.0.1") .usingPort(4734)
		 * .withArgument(GeneralServerFlag.ROBOT_ADDRESS, udid as String)
		 * .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, ((port as int) + 2) as
		 * String) .withArgument(SESSION_OVERRIDE) .withLogFile(new
		 * File("build/${device}.log")));
		 */
		Integer port =4743;

		AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/bin/appium")).withIPAddress("127.0.0.1").usingPort(port)
						);

		service.start();
		System.out.println("Appium started.");
		Thread.sleep(6000000);
		// service.stop();
		System.out.println("Appium CLOSED");
	}
	
	public static AppiumDriverLocalService startNGetAppium(String  ipAddress, Integer port) throws InterruptedException {
		 
		//port =4743;

		AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/bin/appium")).withIPAddress(ipAddress).usingPort(port)
						);

		service.start();
		System.out.println("Appium started at "+ipAddress+":"+port);
		 
		// service.stop();
		return service;
		 
	}

	public static void stopAppium(AppiumDriverLocalService servoce) {

	}
}
