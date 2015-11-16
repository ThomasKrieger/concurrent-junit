package com.anarsoft.vmlens.concurrent.junit.internal;

import org.junit.internal.runners.model.EachTestNotifier;

public class TestResultFailure extends TestResult {

	 private final Throwable error;

	public TestResultFailure(Throwable error) {
		super();
		this.error = error;
	}

	@Override
	public void addFailure(EachTestNotifier eachTestNotifier) {
		eachTestNotifier.addFailure(error);
		
	}

	
	 
	 
	
}
