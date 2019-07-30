package org.boyalla.appium.server;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

	public static void main(String[] args) throws InterruptedException {
		startAppium();

	}

	public static AppiumDriverLocalService getAppium() {
		return null;

	}
	/*
	 * Export as jar file 
	 * java -jar iosserver.jar
	 * 
	 * and export the test to run
	 * 
	 * $ java -jar iostest.jar
	 * 
	 */

	public static void startAppium () throws InterruptedException {
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

		AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/bin/appium")).withIPAddress("127.0.0.1").usingPort(4743)
						.withArgument(GeneralServerFlag.ROBOT_ADDRESS, "emulator-5554"));

		service.start();
		System.out.println("Appium started.");
		Thread.sleep(100000);
		// service.stop();
		System.out.println("Appium CLODED");
	}

	public static void stopAppium(AppiumDriverLocalService servoce) {

	}
}
