package com.anarsoft.vmlens.concurrent.junit.internal;

import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runners.model.Statement;

public class ConcurrentStatement {

	public static TestResult evaluateStatement(Statement statement)
	{
		 try {
	            statement.evaluate();
	        } catch (AssumptionViolatedException e) {
	        	return new TestResultAssumptionViolated(e);
	        } catch (Throwable e) {
	        	return new TestResultFailure(e);
	        }
		 
		 return new TestResultSuccess();
	}
	
	
	private final Statement statement;
	private final  EachTestNotifier eachTestNotifier;
	private TestResult testResult;
	
	public ConcurrentStatement(Statement statement,
			EachTestNotifier eachTestNotifier) {
		super();
		this.statement = statement;
		this.eachTestNotifier = eachTestNotifier;
	}
	
	
	public void addFailures()
	{
		testResult.addFailure(eachTestNotifier);
	}
	
	
	public void evaluate()
	{
		testResult = evaluateStatement(statement);
	}
	
}
