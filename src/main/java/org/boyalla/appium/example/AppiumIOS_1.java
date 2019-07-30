package org.boyalla.appium.example;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumIOS_1 {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2");
		//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
		
		capabilities.setCapability(MobileCapabilityType.APP, "http://appium.s3.amazonaws.com/TestApp7.1.app.zip");
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		AppiumDriver driver = new AppiumDriver(url, capabilities);
		
		//Thread.sleep(10000);
		
		System.out.println("DONE");
		}
}
