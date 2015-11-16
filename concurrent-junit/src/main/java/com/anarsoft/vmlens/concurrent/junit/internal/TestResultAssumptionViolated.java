package com.anarsoft.vmlens.concurrent.junit.internal;

import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;

public class TestResultAssumptionViolated extends TestResult {

	 private final AssumptionViolatedException error;

	public TestResultAssumptionViolated(
			AssumptionViolatedException error) {
		
		this.error = error;
	}

	@Override
	public void addFailure(EachTestNotifier eachTestNotifier) {
		eachTestNotifier.addFailedAssumption(error);
		
	}

     
	
}
