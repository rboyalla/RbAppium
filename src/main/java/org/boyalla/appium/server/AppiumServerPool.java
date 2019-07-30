package org.boyalla.appium.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

//import org.omg.PortableServer.THREAD_POLICY_ID;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerPool {
	
	static HashMap< String, AppiumDriverLocalService> pool=new HashMap<String, AppiumDriverLocalService>();
	
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
//		AppiumDriverLocalService appiumDriverLocalService1=AppiumIOSServer.startNGetAppium("127.0.0.1", 4743);
//		
//		AppiumDriverLocalService appiumDriverLocalService2=AppiumIOSServer.startNGetAppium("127.0.0.1", 4753);
//		
//		Thread.sleep(600000);
//		appiumDriverLocalService1.stop();
//		appiumDriverLocalService2.stop();
//		Thread.sleep(6000);
		
	}

}
