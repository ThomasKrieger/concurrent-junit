package com.anarsoft.vmlens.concurrent.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
@RunWith(ConcurrentTestRunner.class)
public class TestDeadlockAtomicValue {
	private final AtomicPositiveValue first;
	private final AtomicPositiveValue second;
	public TestDeadlockAtomicValue() throws Exception
	{
		first  = new AtomicPositiveValue(1000);
		second = new AtomicPositiveValue(1000);
	}
	@Test
	public void testTransferFirstToSecond() throws Exception
	{
		second.transfer( first , 1);
	}
	@Test
	public void testTransferSecondToFirst() throws Exception
	{
		first.transfer( second , 1);
	}
}
