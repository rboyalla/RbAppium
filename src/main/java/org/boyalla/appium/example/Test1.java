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
import org.boyalla.appium.server.FwAppiumDriverPool;
import org.boyalla.appium.server.FwAppiumDriverPoolSingleton;
import org.boyalla.appium.server.ServiceAndDriver;
import org.boyalla.appium.server.SimulatorMnager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Test1 {

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

    public void appTest2(FwAppiumDriver driver)
            throws MalformedURLException, InterruptedException {
        
        System.out.println("\n\n CLOSING APP ***********************************************************");
        
        driver.closeApp();
        Thread.sleep(5000);
        System.out.println("\n\n CLOSING APP ***********************************************************");
        driver.launchApp();
         Thread.sleep(5000);
        
        //Thread.sleep(5000);
        //WebElement textBox = driver.findElement(By.name("enterUserName"));
        WebElement textBox = driver.findElementByAccessibilityId("enterUserNameId");
        textBox.sendKeys(Util.getNextValue() + ":");
        System.out.println("\n\n ***********************************************************");
        System.out.println("org.boyalla.appium.example.Test1.appTest2()" + textBox.getText());
        System.out.println("***********************************************************\n\n ");
        //Thread.sleep(5000);
        //Thread.sleep(100);
//		WebElement button = driver.findElement(By.name("idb1"));
//		System.out.println("######################## is driver : Value => "+driver.getAppiumUrl()+":"+button.getAttribute("value"));
//		button.click();
//		
        //System.out.println("########################  is enabled: "+button.getAttribute("enabled"));
        //System.out.println("######################## is driver : Value => "+driver.getAppiumUrl()+":"+button.getAttribute("value"));		

        System.out.println(driver.getAppiumUrl() + ":" + "DONE");

        Thread.sleep(5000);
    }

    public void appTest3(FwAppiumDriver driver)
            throws MalformedURLException, InterruptedException {
        //Thread.sleep(5000);
        //WebElement textBox = driver.findElement(By.name("enterUserName"));
        WebElement textBox = driver.findElementByAccessibilityId("enterUserNameId");
        textBox.click();
        //driver.getKeyboard().sendKeys(Util.getNextValue()+":");
        // textBox.click();
        Exception e = null;
        boolean done = true;
        for (int i = 0; i < 6; i++) {
            done = true;
            try {
                Thread.sleep(3000);
                textBox.sendKeys(Util.getNextValue() + ":");
            } catch (Exception ex) {
                System.out.println("org.boyalla.appium.example.Test1.appTest2( Exception) count=" + i);
                //ex.printStackTrace();
                done = false;
                e = ex;
            }

            if (done) {
                break;
            }
        }
        if (!done) {
            System.out.println("*******************************************************************************************");
            System.out.println("org.boyalla.appium.example.Test1.appTest2( Exception) FAILED");
            e.printStackTrace();
            System.out.println("*******************************************************************************************");

        }

        //Thread.sleep(5000);
        //Thread.sleep(100);
//		WebElement button = driver.findElement(By.name("idb1"));
//		System.out.println("######################## is driver : Value => "+driver.getAppiumUrl()+":"+button.getAttribute("value"));
//		button.click();
//		
        //System.out.println("########################  is enabled: "+button.getAttribute("enabled"));
        //System.out.println("######################## is driver : Value => "+driver.getAppiumUrl()+":"+button.getAttribute("value"));		
        System.out.println(driver.getAppiumUrl() + ":" + "DONE");

        //Thread.sleep(100);
    }

    public void appTest(FwAppiumDriver driver)
            throws MalformedURLException, InterruptedException {

        WebElement textBox = driver.findElement(By.name("txt1"));
        textBox.sendKeys(Util.getNextValue() + ":");
        //Thread.sleep(5000);
        //Thread.sleep(100);
        WebElement button = driver.findElement(By.name("idb1"));
        System.out.println("######################## is driver : Value => " + driver.getAppiumUrl() + ":" + button.getAttribute("value"));
        button.click();

        //System.out.println("########################  is enabled: "+button.getAttribute("enabled"));
        System.out.println("######################## is driver : Value => " + driver.getAppiumUrl() + ":" + button.getAttribute("value"));
        System.out.println(driver.getAppiumUrl() + ":" + "DONE");

        //Thread.sleep(100);
    }

    public void test2(Integer port) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        // capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, "true");
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
