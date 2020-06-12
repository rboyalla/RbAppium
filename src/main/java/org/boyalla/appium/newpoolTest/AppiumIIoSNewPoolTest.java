package org.boyalla.appium.newpoolTest;

import org.boyalla.appium.example.*;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.boyalla.appium.pool.AppiumEnginePool;
import org.boyalla.appium.pool.AppiumPoolsFactory;
import org.boyalla.exception.FwException;

public class AppiumIIoSNewPoolTest {

    /**
     *
     * @param args
     * @throws InterruptedException
     * @throws IOException
     *
     *
     *
     *
     */
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

        /*
        
        
        
        
        
        {
"app" :"/Users/raveendraboyalla/rb/run/apps/TestAppRB.app",
"platformName" : "iOS",
"platformVersion" : "12.4",
"deviceName" : "iPhone Xs",
"udid" : "6B405220-FF8C-4CE6-A153-74A31FDE0D5E"
}
        
         */
        String[] maxProcessMacCommand = {"echo", "tanusha", "|", "sudo", "-S", "launchctl", "limit", "maxproc", "2000",
            "2500"};
        // SimulatorMnager.executeCommand(maxProcessMacCommand);
        String simName = "iPhone Xs";
        String simVersion = "12.2";
        long timeStart = System.currentTimeMillis();

        Integer noOfSims = 2;
        Integer noOfTests = 1;

        try {
            noOfSims = Integer.valueOf(args[0]);
            noOfTests = Integer.valueOf(args[1]);
        } catch (Exception ex) {

        }

        SimulatorMnager.cleanSimulators(simName, noOfSims);
        String application = "/Users/rave/Downloads/Appium.app";
        application = "/Users/raveendraboyalla/rb/run/apps/TestAppRB.app";
        parallelExecution(simName, simVersion, noOfSims, noOfTests, application);

        System.out.println("###### TESTING (" + noOfSims + ":" + noOfTests + ")TIME TAKEN # "
                + (System.currentTimeMillis() - timeStart) + " #############");

    }

    private static void parallelExecution(String simType, String simVersion, int noOfSims, int noOfTests, String application)
            throws InterruptedException, IOException, ExecutionException {
        try {
            String ipAddress = "127.0.0.1";
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            System.out.println("Executing test1");
            System.out.println("creating simulators:" + noOfSims);
//            for (int i = 0; i <= noOfSims; i++) {
//                SimulatorMnager.cloneSimulator(simName, simName + i);
//            }
//            FwAppiumDriverPool pool = FwAppiumDriverPoolSingleton.getPool();
//
//            for (int i = 0; i < noOfSims; i++) {
//                pool.addDriver(gertIosDriver(ipAddress, 4743 + (i * 10), simName + i, simVersion, application));
//            }
            System.out.println("running  tests:" + noOfTests);

            for (int i = 0; i < 1; i++) {
                Future<Boolean> call0 = executor.submit(getTestThread(application, ipAddress, simType, simVersion));
                //Future<Boolean> call1 = executor.submit(getTestThread(application, ipAddress, simType, simVersion));
//            Future<Boolean> call2 = executor.submit(getTestThread(application, ipAddress, simType, simVersion));
//            Future<Boolean> call3 = executor.submit(getTestThread(application, ipAddress, simType, simVersion));
//            Future<Boolean> call4 = executor.submit(getTestThread(application, ipAddress, simType, simVersion));
//
                // executor.execute(getTestThread(application, ipAddress, simName + "1", simVersion));
            }
            //call0.get();
//            call1.get();
//            call2.get();
//            call3.get();
//            call4.get();

            System.out.println("*********BEFORE shutdowned.**********");
            executor.shutdown();
            System.out.println("*********BEFORE Await .**********");
            executor.awaitTermination(60, TimeUnit.MINUTES);

//            for (int i = 0; i < noOfTests; i++) {
//                System.out.println("Deleting sim: " + simName + i);
//                SimulatorMnager.deleteSimulator(simName + i);
//
//            }
            // pool.closePool();
        } finally {
            //  SimulatorMnager.cleanSimulators(simName, noOfSims);
            AppiumPoolsFactory.closePools();
        }
        System.out.println("*********Executor shutdowned.**********");
    }

    private static Callable<Boolean> getTestThread(String appName, String ipAddress, String simType, String osVersion) {
        return new Callable<Boolean>() {

            public void __run() {
                AppiumEnginePool pool = null;
                FwAppiumDriver driver = null;
                try {

                    pool = AppiumPoolsFactory.getPool(appName, ipAddress, simType, osVersion);
                    System.out.println("*************************************************************************");
                    System.out.println(Thread.currentThread().getId());

                    System.out.println("************************************************ borrow object for=" + simType);
                    System.out.println("*************************************************************************");

                    driver = pool.borrowObject();
                    System.out.println("*************************************************************************");
                    System.out.println("************************************************ borrowed object for=" + simType);
                    System.out.println("************************************************ driver:" + driver.getAppiumUrl());
                    System.out.println("*************************************************************************");

                    new Test1().appTest2(driver);
                } catch (MalformedURLException e) {
                    throw new FwException(e);
                } catch (InterruptedException e) {
                    throw new FwException(e);
                } catch (Exception ex) {
                    throw new FwException(ex);
                } finally {
                    if (pool != null && driver != null) {
                        pool.returnObject(driver);
                    }

                }
            }

            @Override
            public Boolean call() throws Exception {
                __run();
                return true;
            }
        };
    }

//    private static Thread getTestThread(FwAppiumDriver driver) {
//        return new Thread() {
//
//            public void run() {
//                try {
//                    FwAppiumDriverPool pool = FwAppiumDriverPoolSingleton.getPool();
//                    FwAppiumDriver driver = pool.getDriver();
//                    System.out.println("************ driver:" + driver.getAppiumUrl());
//                    //new Test1().appTest(driver);
//                    new Test1().appTest2(driver);
//                    pool.returnDiver(driver);
//                    // new AppiumIOS_AppTest().browserTest(port, deviceName, osVersion);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//    }
//    public static FwAppiumDriver gertIosDriver(String ipAddress, Integer port, String iPhone, String osVersion, String application)
//            throws MalformedURLException, InterruptedException {
//        //String ipAddress = "127.0.0.1";
//        AppiumDriverLocalService service = AppiumIOSServer.startNGetAppium(ipAddress, port);
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
//        capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, "true");
//        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "180");
//
//        // capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, iPhone);
//        // capabilities.setCapability(MobileCapabilityType.UDID, uidd);
//
//        // capabilities.setCapability(IOSMobileCapabilityType.WEB_DRIVER_AGENT_URL,
//        // "http://127.0.0.1:" + (port+4000) );
//        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, (port + 4000));
//        // IOSMobileCapabilityType.XCODE_CONFIG_FILE
//        // String application = "/Users/rave/Downloads/Appium.app";
//        capabilities.setCapability(MobileCapabilityType.APP, application);
//        // URL url = new URL("http://127.0.0.1:" + port + "/wd/hub");
//        // AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url,
//        // capabilities);
//        // AppiumDriver<WebElement> driver = new AppiumDriver(service, capabilities);
//        FwAppiumDriver driver = new FwAppiumDriver(service, capabilities);
//
//        return driver;
//
//    }
}
