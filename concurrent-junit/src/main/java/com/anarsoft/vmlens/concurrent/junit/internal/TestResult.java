package com.anarsoft.vmlens.concurrent.junit.internal;

import org.junit.internal.runners.model.EachTestNotifier;

public abstract class TestResult {
	
	
	
	public TestResult() {
		super();
		
	}
	
	public abstract void addFailure(EachTestNotifier eachTestNotifier );
	
	
	
	

}
