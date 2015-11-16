package com.anarsoft.vmlens.concurrent.junit.internal;



public class ParallelExecutorThread extends Thread {

	private final ConcurrentStatement concurrentStatement;
	public volatile boolean execute = false;
	

	
	public ParallelExecutorThread(ConcurrentStatement concurrentStatement) {
		super();
		this.concurrentStatement = concurrentStatement;
	}




	@Override
	public void run() {
		
		try{
		while( ! execute )
		{
			Thread.sleep(1);
		}
		
		concurrentStatement.evaluate();
		
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	
	

}
