package com.anarsoft.vmlens.concurrent.example;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

import org.junit.Assert;
import org.junit.Before;
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

	

	private TypeVariable typeVariable;
	
	
	
	@Before
	public void setUp() throws NoSuchMethodException, SecurityException
	{
		Class cl = GenericInterface.class;
		

		typeVariable  = cl.getTypeParameters()[0];
		
		
	
	}
	
	
	@Test
	public void test()
	{
		Assert.assertTrue( typeVariable.getBounds()[0] instanceof  ParameterizedType );
	
	}
}
