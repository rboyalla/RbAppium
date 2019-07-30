package org.boyalla.appium.example;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumIOsBrowser {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// java

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");

		capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");
		// capabilities.setCapability(MobileCapabilityType.APP,
		// "http://appium.s3.amazonaws.com/TestApp7.1.app.zip");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		System.out.println("Before appium driver");
		WebDriver driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
		System.out.println("Before  get");
		
		driver.get("http://saucelabs.com/test/guinea-pig");
		Thread.sleep(8000);
		driver.get("http://google.com");
		Thread.sleep(8000);
		driver.get("http://gmail.com");
		Thread.sleep(8000);
		/*
WebElement search = driver.findElement(By.id("lst-ib"));
		
		//WebElement search = diver.findElementsByAccessibilityId("Search");
		search.sendKeys("naked beach girls");
		
		WebElement searchbutton = driver.findElement(By.id("tsbb"));
		
		searchbutton.click();
		Thread.sleep(8000);
		System.out.println("Before closing appium driver");
		*/
		driver.quit();

		/*
		 * desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
		 * "Safari"); URL url = new URL("http://127.0.0.1:4723/wd/hub"); AppiumDriver
		 * driver = new AppiumDriver(url, desiredCapabilities);
		 * 
		 * // Navigate to the page and interact with the elements on the guinea-pig page
		 * using id. driver.get("http://saucelabs.com/test/guinea-pig"); WebElement div
		 * = driver.findElement(By.id("i_am_an_id"));
		 * //Assert.assertEquals("I am a div", div.getText()); //check the text
		 * retrieved matches expected value
		 * driver.findElement(By.id("comments")).sendKeys("My comment"); //populate the
		 * comments field by id.
		 * 
		 * //close the app. driver.quit();
		 */
		System.out.println("DONE");
	}
}
