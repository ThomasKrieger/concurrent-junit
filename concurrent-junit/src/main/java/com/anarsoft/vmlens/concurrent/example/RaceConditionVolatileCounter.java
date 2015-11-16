package com.anarsoft.vmlens.concurrent.example;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;



/**
 * 
 * Shows how to use org.junit.After annotation to check for the result of a computation. 
 * The assertion will fail when run with <a href="http://vmlens.com">vmlens.com</a> with
 * "delay synchronization for unit tests" enabled.
 * 
 * 
 * 
 * @author Thomas
 *
 */

@RunWith(ConcurrentTestRunner.class)
public class RaceConditionVolatileCounter {
	
	
	private volatile int i = 0;
	
	@Test
	public void addOne()
	{
		i++;
	}
	
	@After
	public void assertCount()
	{
		assertEquals("4 Threads running addOne in parallel should lead to 4" , 4 , i);
	}

}
