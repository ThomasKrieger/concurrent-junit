package com.anarsoft.vmlens.concurrent.example;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

/**
 * 
 * {@link #addOne} is run by 4 threads in parallel. Since it is not synchronized tools like 
 * <a href="http://vmlens.com">vmlens.com</a> will show a race condition.
 * 
 * @author Thomas
 *
 */

@RunWith(ConcurrentTestRunner.class)
public class RaceConditionMissingSynchronization {
	
	
	private MutableInt mutableInt = new MutableInt();
	
	@Test
	public void addOne()
	{
		mutableInt.add(1);
	}

}
