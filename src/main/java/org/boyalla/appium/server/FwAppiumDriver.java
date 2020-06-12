package org.boyalla.appium.server;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.boyalla.exception.FwException;

public class FwAppiumDriver extends AppiumDriver<WebElement> {

    public boolean isFree = true;
    AppiumDriverLocalService aService;
    private String simName;

    public FwAppiumDriver(AppiumDriverLocalService service, Capabilities desiredCapabilities) {
        super(service, desiredCapabilities);
        this.aService = service;

    }

    public FwAppiumDriver(AppiumDriverLocalService service, String simName, Capabilities desiredCapabilities) {
        super(service, desiredCapabilities);
        this.aService = service;

        this.simName = simName;

    }

    public FwAppiumDriver(AppiumDriverLocalService aService, String simName, URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
        this.aService = aService;
        this.simName = simName;
    }

    public void stopService() {
        aService.stop();
    }

    @Override
    public void close() {
        try {
            SimulatorMnager.deleteSimulator(simName);
        } catch (IOException ex) {
            throw new FwException(ex);
        } catch (InterruptedException ex) {
            throw new FwException(ex);
        } finally {
            super.close();
        }

    }

    public String getAppiumUrl() {

        return "DriverTo:" + aService.getUrl().toString();
    }

}
