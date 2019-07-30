package org.boyalla.appium.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.boyalla.appium.server.AppiumIOSServer;
import org.boyalla.appium.server.FwAppiumDriver;
import org.boyalla.appium.server.SimulatorMnager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumIOS_AppTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		parallelExecution(Integer.valueOf(args[0]));

		System.out.println("################## TESTING COMPLETED #################");
	}

	private static void parallelExecution(int noOfSims) throws InterruptedException, IOException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);

		System.out.println("Executing test1");
		String simName = "iPhone 7";

		System.out.println("Executing test2");
		//int noOfSims=2;
		for (int i = 0; i < noOfSims; i++) {
			SimulatorMnager.cloneSimulator(simName, simName + i);
			//executor.execute(getThread(4743 + (i * 10), simName + i, "11.0"));
		}
		for (int i = 0; i < noOfSims; i++) {
			//SimulatorMnager.cloneSimulator(simName, simName + i);
			executor.execute(getThread(4743 + (i * 10), simName + i, "11.1"));
		}

		// can clone iphone simulators of your choice using commandline tools

		// executor.execute(getThread(4763,"iPhone 6"));
		// executor.execute(getThread(4773,"iPhone 6 Plus"));

		System.out.println("*********BEFORE shutdowned.**********");
		executor.shutdown();
		System.out.println("*********BEFORE Await .**********");
		executor.awaitTermination(60, TimeUnit.MINUTES);

		for (int i = 0; i < noOfSims; i++) {
			System.out.println("Deleting sim: "+simName + i);
			SimulatorMnager.deleteSimulator(simName + i);

		}
		 
		 
		// SimulatorMnager.shutdownSimulator(simName);
		System.out.println("*********Executor shutdowned.**********");
	}

	private static Thread getThread(final Integer port, final String deviceName, final String osVersion) {
		return new Thread() {

			public void run() {
				try {
					new AppiumIOS_AppTest().appTest(port, deviceName, osVersion);
					// new AppiumIOS_AppTest().browserTest(port, deviceName, osVersion);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	public void browserTest(Integer port, String iPhone, String osVersion)
			throws MalformedURLException, InterruptedException {
		AppiumDriverLocalService service = AppiumIOSServer.startNGetAppium("127.0.0.1", port);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, iPhone);

		// capabilities.setCapability(IOSMobileCapabilityType.WEB_DRIVER_AGENT_URL,
		// "http://127.0.0.1:" + (port+4000) );
		// capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT,
		// (port+4000) );
		// IOSMobileCapabilityType.XCODE_CONFIG_FILE
		// capabilities.setCapability(MobileCapabilityType.APP,
		// "/Users/raveendra/Downloads/Rave.app");
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		URL url = new URL("http://127.0.0.1:" + port + "/wd/hub");
		AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url, capabilities);

		System.out.println("Before  get");

		driver.get("http://saucelabs.com/test/guinea-pig");
		Thread.sleep(8000);
		driver.get("http://google.com");
		Thread.sleep(8000);
		driver.get("http://gmail.com");
		Thread.sleep(8000);
		driver.quit();
		service.stop();

		// Thread.sleep(10000);

		System.out.println("DONE");
	}

	public void appTest(Integer port, String iPhone, String osVersion)
			throws MalformedURLException, InterruptedException {
		AppiumDriverLocalService service = AppiumIOSServer.startNGetAppium("127.0.0.1", port);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, iPhone);
		// capabilities.setCapability(MobileCapabilityType.UDID, uidd);

		// capabilities.setCapability(IOSMobileCapabilityType.WEB_DRIVER_AGENT_URL,
		// "http://127.0.0.1:" + (port+4000) );
		capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, (port + 4000));
		// IOSMobileCapabilityType.XCODE_CONFIG_FILE
		capabilities.setCapability(MobileCapabilityType.APP, "/Users/rave/Downloads/Appium.app");
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		//URL url = new URL("http://127.0.0.1:" + port + "/wd/hub");
		//AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url, capabilities);
		//AppiumDriver<WebElement> driver = new AppiumDriver(service, capabilities);
		FwAppiumDriver driver = new FwAppiumDriver(service, capabilities);

		
		
		WebElement textBox = driver.findElement(By.name("txt1"));
		textBox.sendKeys("raveendra");
		//Thread.sleep(5000);
		WebElement button = driver.findElement(By.name("idb1"));
		System.out.println("######################## is iPhone : Value => "+iPhone+" : "+button.getAttribute("value"));
		button.click();
		//Thread.sleep(5000);
		//System.out.println("########################  is enabled: "+button.getAttribute("enabled"));
		System.out.println("######################## is iPhone : Value => "+iPhone+" : "+button.getAttribute("value"));		
		driver.quit();
		service.stop();

		// Thread.sleep(10000);

		System.out.println("DONE");
	}

	public void test2(Integer port) throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");

		capabilities.setCapability(MobileCapabilityType.APP, "/Users/raveendra/Downloads/Appium.app");
		URL url = new URL("http://127.0.0.1:" + port + "/wd/hub");
		AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url, capabilities);

		WebElement textBox = driver.findElement(By.name("txt1"));
		textBox.sendKeys("raveendra");
		Thread.sleep(10000);
		WebElement button = driver.findElement(By.name("idb1"));
		button.click();
		Thread.sleep(10000);
		driver.quit();

		// Thread.sleep(10000);

		System.out.println("DONE");
	}
}
