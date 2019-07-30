package org.boyalla.appium.server;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class ServiceAndDriver {
	AppiumDriverLocalService service;
	AppiumDriver<WebElement> driver;
	public ServiceAndDriver(AppiumDriverLocalService service,AppiumDriver<WebElement> driver ) {
		this.service=service;
		this.driver=driver;
		 
	}
	public AppiumDriver<WebElement> getDriver(){
		return driver;
	}
	public void stopService() {
		service.stop();
	}
}
