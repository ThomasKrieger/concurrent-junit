package com.anarsoft.vmlens.concurrent.example;


import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;


/**
 * 
 * {@link #test} is run by 4 threads in parallel. A tool like 
 * <a href="http://vmlens.com">vmlens.com</a> will show a race condition in the method call getBounds.
 * 
 * @author Thomas
 *
 */

@RunWith(ConcurrentTestRunner.class)
public class RaceConditionInJavaReflection {

	
	@Test
	public void testGetBounds()
	{
		Class cl = GenericInterface.class;
		TypeVariable typeVariable  = cl.getTypeParameters()[0];
		
		for(  Type bound :  typeVariable.getBounds() )
		{
	       // Do something		
		}
	
	}
}
