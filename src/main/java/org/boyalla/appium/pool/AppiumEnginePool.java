/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.boyalla.appium.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.boyalla.appium.server.FwAppiumDriver;

/**
 *
 * @author raveendraboyalla
 */
public class AppiumEnginePool extends GenericObjectPool<FwAppiumDriver> {

    public AppiumEnginePool(PooledObjectFactory<FwAppiumDriver> factory) {
        
        super(factory);
        //GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //config.setMaxTotal(3);
        setMaxTotal(3);
        setMinIdle(3);
    }

    @Override
    public void returnObject(FwAppiumDriver obj) {
       // obj.resetApp();
      // obj.resetInputState();
        
        //obj.closeApp();
        super.returnObject(obj); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public FwAppiumDriver borrowObject() throws Exception {
        final FwAppiumDriver borrowObject = super.borrowObject();
        // borrowObject.resetApp();
        return borrowObject; //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public String toString() {
        return getFactory().toString();
    }

    
}
