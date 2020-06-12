/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.boyalla.appium.pool;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.pool2.BasePooledObjectFactory;

/**
 *
 * @author raveendraboyalla
 */
public class AppiumPoolsFactory {

   private static final List<AppiumEnginePool> POOLS = new ArrayList<>();

    public synchronized static AppiumEnginePool getPool(String appName, String ipAddress, String simType, String osVersion) {
        
        
        System.out.println("org.boyalla.appium.pool.AppiumPoolsFactory.getPool() START Thread="+
                +Thread.currentThread().getId());
        AppiumEngineFactory appiumEngineFactory = new AppiumEngineFactory(appName, ipAddress, simType, osVersion);

        for (AppiumEnginePool pool : POOLS) {
            
            AppiumEngineFactory aef=(AppiumEngineFactory)pool.getFactory();
            System.out.println("aef="+aef);
            System.out.println("appiumEngineFactory="+appiumEngineFactory);
            
             System.out.println("aef.equals(appiumEngineFactory)="+aef.equals(appiumEngineFactory));
            if (aef.equals(appiumEngineFactory)) {
                System.out.println();
                System.out.println("***** RETURING POOL *****************************************************************************");
                System.out.println("org.boyalla.appium.pool.AppiumPoolsFactory.getPool() appiumEnginePool=" + pool+":"+pool.getMaxTotal());
                System.out.println("***** RETURNED POOL *****************************************************************************");
                System.out.println();
                System.out.println("org.boyalla.appium.pool.AppiumPoolsFactory.getPool() END Thread="+
                +Thread.currentThread().getId());
                return pool;
            }
        }
        final AppiumEnginePool appiumEnginePool = new AppiumEnginePool(appiumEngineFactory);
        System.out.println();
        System.out.println("***** CREATED POOL *****************************************************************************");
        System.out.println("org.boyalla.appium.pool.AppiumPoolsFactory.getPool() appiumEnginePool=" + appiumEnginePool+":"+appiumEnginePool.getMaxTotal());
        POOLS.add(appiumEnginePool);
        System.out.println("***** ADDED POOL *****************************************************************************");
        System.out.println();
        System.out.println("org.boyalla.appium.pool.AppiumPoolsFactory.getPool() END Thread="+
                +Thread.currentThread().getId());
        return appiumEnginePool;
    }
    
    public static void closePools(){
        POOLS.forEach((pool) -> {
            pool.close();
       });
    }

}
