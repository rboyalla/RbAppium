package org.boyalla.appium.example;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;

public class AppiumIOS_AppTestXcode7 {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        File classpathRoot = new File(System.getProperty("user.dir"));
        //FileSystems.getDefault().getPath(".").toAbsolutePath();
        //File appDir = new File(classpathRoot, "/Users/rb/rb/proj/RbAppium/apps");
        File appDir = new File(classpathRoot, "apps");
        //File app = new File(appDir, "TestAppRB.app");
        //File app = new File("/Users/rb/rb/proj/RbAppium/apps/TestAppRB.app");
        File app = new File(appDir, "TestAppRB.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4");
        //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 Pro Max");

        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url, capabilities);

        WebElement textBox = driver.findElement(By.name("User Name"));
        System.out.println(" ######## before entering user name");
        textBox.sendKeys("raveendra");
        System.out.println(" ######## enetered user name");
        Thread.sleep(10000);
        WebElement button = driver.findElement(By.name("Set Default Label Text"));
        System.out.println(" ######## enetered user name");
        button.click();
        Thread.sleep(1000);
        driver.quit();

        //Thread.sleep(10000);
        System.out.println("DONE");
    }
}
