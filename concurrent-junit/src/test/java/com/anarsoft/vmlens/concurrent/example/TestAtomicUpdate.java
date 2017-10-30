package com.anarsoft.vmlens.concurrent.example;

import org.junit.Test;
import static  org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;


public class TestAtomicUpdate {
	
	@Test
	public void testSetAndGetParallel() throws Exception
	{
		AtomicInteger atomicInteger= new AtomicInteger(0);
		int threadA = atomicInteger.get();
		int threadB = atomicInteger.get();
		atomicInteger.set(threadA + 5);
		atomicInteger.set(threadB + 5);
		assertEquals(   atomicInteger.get() , 5  );
	}
	public void testSetAndGetSequential() throws Exception
	{
		AtomicInteger atomicInteger= new AtomicInteger(0);
		int threadA = atomicInteger.get();
		atomicInteger.set(threadA + 5);
		int threadB = atomicInteger.get();
		atomicInteger.set(threadB + 5);
		assertEquals(   atomicInteger.get() , 10  );
	}
	
	
	@Test
	public void testUpdate() throws Exception
	{
		AtomicInteger atomicInteger= new AtomicInteger(0);
		atomicInteger.addAndGet(5); // Thread A
		atomicInteger.addAndGet(5); // Thread B
		assertEquals(   atomicInteger.get() , 10  );
	}
	

}
