package com.anarsoft.vmlens.concurrent.junit;

import java.util.Set;

import org.junit.runner.Request;
import org.junit.runner.notification.RunNotifier;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class JUnitCoreParallelize {

	public static void runTest(String  packageName) throws Exception {
		
		
//	Class cl = 	JUnitCoreParallelize.class.getClassLoader().loadClass("com.fasterxml.jackson.core.json.ArrayGenerationTest");
//		
//
//    final Request req =	Request.aClass(cl);
//    final RunNotifier notifier = new RunNotifier();
//	
//    TestUtil.runMultithreaded( new Runnable()
//    		{
//
//				public void run() {
//					
//					
//					req.getRunner().run(notifier);
//					
//					
//				}
//    	
//    		}, 5);
    
	      Reflections reflections = new Reflections(packageName , new SubTypesScanner(false) , JUnitCoreParallelize.class.getClassLoader() );

	      Set subTypes = reflections.getSubTypesOf(Object.class);

		
	      
	      for( Object obj :  subTypes )
	      {
	    	  System.out.println(obj);
	      }
	      
		
    
    
	}

}
