/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.boyalla.appium.pool;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.boyalla.appium.drivers.AppiumDriverUtil;
import org.boyalla.appium.server.FwAppiumDriver;
import org.boyalla.appium.server.SimulatorMnager;
import org.boyalla.util.FwSysUtil;

/**
 *
 * @author raveendraboyalla
 */
public class AppiumEngineFactory extends BasePooledObjectFactory<FwAppiumDriver> {

    String appName;
    String ipAddress;
    String simType;
    String osVersion;
    AtomicInteger simcount = new AtomicInteger(1);

    public AppiumEngineFactory(String appName, String ipAddress, String simType, String osVersion) {
        this.appName = appName;
        this.ipAddress = ipAddress;
        this.simType = simType;
        this.osVersion = osVersion;
    }

    @Override
    public String toString() {
        return "appName:" + appName + ";" + "ipAddress:" + ipAddress + ";" + "simType:" + simType + ";" + "osVersion:" + osVersion;
    }

    @Override
    public void destroyObject(final PooledObject<FwAppiumDriver> p)
            throws Exception {
        final FwAppiumDriver object = p.getObject();
        System.out.println("org.boyalla.appium.pool.AppiumEngineFactory.destroyObject() "+object );
        //object.closeApp();
         object.close();
    }

    @Override
    public FwAppiumDriver create() throws Exception {
        final Integer freePort = FwSysUtil.getFreePort();
        final int andIncrement = simcount.getAndIncrement();
        System.out.println();
        System.out.println("************************************************************************");
        System.out.println("org.boyalla.appium.pool.AppiumEngineFactory.create() AppiumEngineFactory="+this);
        System.out.println("     freePort:simType:andIncrement=" + freePort + ":" + simType + ":" + andIncrement);
        System.out.println("************************************************************************");
        System.out.println();

        return AppiumDriverUtil.gertIosDriver(appName, ipAddress, freePort, simType, andIncrement, osVersion);
    }

    @Override
    public PooledObject<FwAppiumDriver> wrap(FwAppiumDriver t) {
        return new DefaultPooledObject<>(t);
    }

    @Override
    public boolean equals(Object appiumEngineFactory) {
        
        if (!(appiumEngineFactory instanceof AppiumEngineFactory)) {
            return false;
        }
        if (appiumEngineFactory instanceof AppiumEngineFactory && appiumEngineFactory == this) {
            return true;
        }
        AppiumEngineFactory engineFactory = (AppiumEngineFactory) appiumEngineFactory;

        if (!engineFactory.simType.equals(this.simType)) {
            return false;
        }
        if (!engineFactory.ipAddress.equals(this.ipAddress)) {
            return false;
        }
        if (!engineFactory.appName.equals(this.appName)) {
            return false;
        }
        return engineFactory.osVersion.equals(this.osVersion);

    }

    @Override
    public int hashCode() {
//        int hash = 3;
//        hash = 97 * hash + Objects.hashCode(this.appName);
//        hash = 97 * hash + Objects.hashCode(this.ipAddress);
//        hash = 97 * hash + Objects.hashCode(this.simType);
//        hash = 97 * hash + Objects.hashCode(this.osVersion);
//        return hash;
    return 1;
    }

}
