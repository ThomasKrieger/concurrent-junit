package com.anarsoft.vmlens.concurrent.junit.internal;



public class ParallelExecutorThread extends Thread {

	private final ConcurrentStatement concurrentStatement;
	
	

	
	public ParallelExecutorThread(ConcurrentStatement concurrentStatement	) {
		super();
		this.concurrentStatement = concurrentStatement;
	}




	@Override
	public void run() {
		
		try{
		
		concurrentStatement.evaluate();
		
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	
		
	}
	
	
	

}
