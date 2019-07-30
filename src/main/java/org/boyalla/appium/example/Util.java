package org.boyalla.appium.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Util {
	static AtomicInteger ai=new AtomicInteger();
	public static Integer getNextValue() {
		   return ai.incrementAndGet();
		  
	}

}
