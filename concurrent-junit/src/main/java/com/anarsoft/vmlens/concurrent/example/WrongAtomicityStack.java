package com.anarsoft.vmlens.concurrent.example;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

/**
 * 
 * Shows how to use org.junit.Before annotation to create the pre-conditions of a concurrent tests. {@link #addOne} will be only called once, {@link #removeOne} 4 times in parallel.
 * When you run this test with <a href="http://vmlens.com">vmlens.com</a> with "delay synchronization for unit tests" enabled,  {@link #removeOne} will 
 * throw a java.util.EmptyStackException.
 * 
 * 
 * 
 * @author Thomas
 *
 */
@RunWith(ConcurrentTestRunner.class)
public class WrongAtomicityStack {
	
	private  final Stack<String> stack = new Stack<String>();
	
	@Before
	public void addOne() throws Exception
	{		
		stack.push("abcd");
	}
	
	@Test
	public void removeOne() throws Exception
	{		
		if( ! stack.isEmpty() )
		{				
			stack.pop();
		}
	}
	

}
