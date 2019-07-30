package org.boyalla.appium.server;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class FwAppiumDriver2 extends AppiumDriver<WebElement>{

	public boolean isFree=true;
	AppiumDriverLocalService aService;
	public FwAppiumDriver2(AppiumDriverLocalService service, Capabilities desiredCapabilities) {
		super(service, desiredCapabilities);
		this.aService=service;
		 
	} 
	
	public void quit() {
		this.isFree=true;
		
	}
	public void stopService() {
		aService.stop();
	}
	public String getAppiumUrl() {
		
		return "DriverTo:"+aService.getUrl().toString();
	}

}
