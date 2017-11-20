package com.anarsoft.vmlens.concurrent.junit;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * static utility methods for testing methods concurrently.
 * 
 * @author thomas
 *
 */


public class TestUtil {

	
	/**
	 * 
	 * Runs the runnables with threadCount threads in parallel.
	 * Example usage:
	 * <pre>
	 *     	TestUtil.runMultithreaded( new Runnable() {
	 *		public void run() {
	 *			try{
	 *				shouldFindAllPetTypes();
	 *			}
	 *			catch(Exception e)
	 *			{
	 *				e.printStackTrace();
	 *			}
	 *		}
     *	}
     *	, 5);
	 * </pre>
	 * 
	 * 
	 * @param runnable
	 * @param threadCount
	 * @throws InterruptedException
	 */
	
	
	public static void runMultithreaded(Runnable  runnable, int threadCount) throws InterruptedException
	{
		List<Thread>  threadList = new LinkedList<Thread>();
		
		for(int i = 0 ; i < threadCount; i++)
		{
			threadList.add(new Thread(runnable));
		}
		
		for( Thread t :  threadList)
		{
			t.start();
		}
		
		
		for( Thread t :  threadList)
		{
			t.join();
		}
		
		
		
		
	}
	
	
	
}
