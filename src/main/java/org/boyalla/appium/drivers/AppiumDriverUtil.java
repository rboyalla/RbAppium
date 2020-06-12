/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.boyalla.appium.drivers;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.IOException;
import java.net.MalformedURLException;
import org.boyalla.appium.server.AppiumIOSServer;
import org.boyalla.appium.server.FwAppiumDriver;
import org.boyalla.appium.server.SimulatorMnager;
import org.boyalla.util.FwSysUtil;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author raveendraboyalla
 */
public class AppiumDriverUtil {

    public static FwAppiumDriver gertIosDriver(String appName, String ipAddress, Integer port, String simType,
            int simNumber, String osVersion
    )
            throws MalformedURLException, InterruptedException, IOException {

        final String simName = simType + simNumber;

        //need to get random port
        // int port=11;
        //create sim and do folloing
        String uid = SimulatorMnager.cloneSimulator(simType, simName);
        //String ipAddress = "127.0.0.1";
        AppiumDriverLocalService service = AppiumIOSServer.startNGetAppium(ipAddress, port);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
        //capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, "true");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "180");
        capabilities.setCapability("isHeadless", false);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        //  capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        // capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        //capabilities.setCapability("useNewWDA", false);
        // capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");

        capabilities.setCapability(MobileCapabilityType.UDID, uid);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, simType);
        // capabilities.setCapability(MobileCapabilityType.UDID, uidd);

        // capabilities.setCapability(IOSMobileCapabilityType.WEB_DRIVER_AGENT_URL,
        // "http://127.0.0.1:" + (port+4000) );
        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, FwSysUtil.getFreePort());
        // IOSMobileCapabilityType.XCODE_CONFIG_FILE
        // String application = "/Users/rave/Downloads/Appium.app";
        capabilities.setCapability(MobileCapabilityType.APP, appName);
        // URL url = new URL("http://127.0.0.1:" + port + "/wd/hub");
        // AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url,
        // capabilities);
        // AppiumDriver<WebElement> driver = new AppiumDriver(service, capabilities);
        FwAppiumDriver driver = new FwAppiumDriver(service, simName, capabilities);
        //

        return driver;

    }
}
