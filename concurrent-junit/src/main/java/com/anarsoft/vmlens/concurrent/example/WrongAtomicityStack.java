package com.anarsoft.vmlens.concurrent.example;


import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

/**
 * 
 * Shows how to use org.junit.Before annotation to create the pre-conditions of a concurrent tests. {@link #setup} will be only called once, {@link #removeOne} 4 times in parallel.
 * When you run this test with <a href="http://vmlens.com">vmlens.com</a> with a waitpoint set at the field java.util.Vector.elementCount in the method removeOne
 * ,  {@link #removeOne} will throw a java.util.EmptyStackException.
 * 
 * 
 * 
 * @author Thomas
 *
 */
@RunWith(ConcurrentTestRunner.class)
public class WrongAtomicityStack {
	
	private  Stack<String> stack = new Stack<String>();
	private volatile boolean emptyStackExceptionThrown = false;
	
	
	@Before
	public void setup() throws Exception
	{		
		stack.push("abcd");
	}
	
	@Test
	public void removeOne() throws Exception
	{		
		try{
		if( ! stack.isEmpty() )
		{				
			stack.pop();
		}
		}
		catch(EmptyStackException exception)
		{
			emptyStackExceptionThrown = true;
		}
	}
	
	@After
	public void checkExceptionThrown()
	{
		assertTrue("When a waitpoint is set an EmptyStackException should be thrown" ,emptyStackExceptionThrown);
	}
	
	

}
