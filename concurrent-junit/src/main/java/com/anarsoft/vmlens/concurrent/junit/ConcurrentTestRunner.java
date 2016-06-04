package com.anarsoft.vmlens.concurrent.junit;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;







import org.junit.After;
import org.junit.Before;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.anarsoft.vmlens.concurrent.junit.internal.ConcurrentStatement;
import com.anarsoft.vmlens.concurrent.junit.internal.InvokeListOfMethods;
import com.anarsoft.vmlens.concurrent.junit.internal.NoOpStatement;
import com.anarsoft.vmlens.concurrent.junit.internal.ParallelExecutorThread;
import com.anarsoft.vmlens.concurrent.junit.internal.TestResult;


/**
 * <p>A <a href="junit.org">JUnit</a> test runner to run concurrent unit tests.  Runs the tests of one test class in the following order:
 * </p>
 * <ol>
 *    <li> Runs methods marked with org.junit.Before annotation in the main thread.</li>
 *    <li> Runs methods marked with org.junit.Test annotation. Each method is run in 4 parallel threads.</li>
 *    <li> Runs methods marked with org.junit.After annotation in the main thread.  </li>
 * </ol>
 * <p>
 * See {@link com.anarsoft.vmlens.concurrent.example.WrongAtomicityStack} for an example on how to use org.junit.Before.

 * See {@link com.anarsoft.vmlens.concurrent.example.RaceConditionVolatileCounter} for an example on how to use org.junit.After.
 * Most useful when using a dynamic race condition catcher like <a href="vmlens.com">vmlens</a>.</p>
 * 
 * 
 * @author Thomas
 *
 */


public class ConcurrentTestRunner   extends BlockJUnit4ClassRunner {

	public ConcurrentTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	
	
	
	
	
	 protected Statement childrenInvoker(final RunNotifier notifier) {
	        return new Statement() {
	            @Override
	            public void evaluate() {
	            	runChildrenConcurrently(notifier);
	            }
	        };
	    }
	 
	 
	 private void runChildrenConcurrently(final RunNotifier notifier) {
	      
		List<EachTestNotifier>  eachTestNotifierList = new LinkedList<EachTestNotifier>();
		List<ConcurrentStatement>  concurrentStatementList = new LinkedList<ConcurrentStatement>();
	    
		
		  Object test;
	        try {
	            test = new ReflectiveCallable() {
	                @Override
	                protected Object runReflectiveCall() throws Throwable {
	                    return createTest();
	                }
	            }.run();
	        } catch (Throwable e) {
	        	 for (FrameworkMethod method : getChildren()) 
	    		 {
	        		 Description description = describeChild(method);
	        		 notifier.fireTestFailure(new Failure(description , e));
	    		 }
	        	 return;
	        }
		
		
		 for (FrameworkMethod method : getChildren()) 
		 {
			 Description description = describeChild(method);
		        if (callIsIgnoredWithReflection(method)) {
		            notifier.fireTestIgnored(description);
		        } else {
		            	        	
		            EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
		   	        eachNotifier.fireTestStarted();
		   	        eachTestNotifierList.add(eachNotifier);
		   	        
		   	        Statement st = createMethodStatement(  method,  test);
		        	
		   	    
		   	     ThreadCount threadCountAnnotation =   method.getAnnotation(ThreadCount.class);
		   	        
		   	     int  threadCount = 4;
		   	     
		   	     if( threadCountAnnotation != null )
		   	     {
		   	    	threadCount = threadCountAnnotation.value();
		   	     }
		   	     
		   	     
		   	     for( int i = 0 ; i < threadCount ; i++ )
		   	     {
		   	    	 concurrentStatementList.add(new ConcurrentStatement(st,eachNotifier));
		   	     }
		   	    	 
		   	    	 
		   	     
		   	    
		   	   
		       
		   	        
		        }
		 }
		
		 
		 Statement before =  createBefores( test);
		 
		 evaluateStatement(before , eachTestNotifierList );
		 
		 
		 
		 
		 List<ParallelExecutorThread>  threadList = new LinkedList<ParallelExecutorThread>();
		 
		 
		
		 
		 for( ConcurrentStatement st : concurrentStatementList  )
		 {
			 ParallelExecutorThread t = new ParallelExecutorThread(st);
		
			 
			
			 
			 threadList.add(t);
			 t.start();
		 }
		 
		 
		 for(  ParallelExecutorThread t : threadList )
		 {
			 t.execute = true;
		 }
		 
		 
		 for(  ParallelExecutorThread t : threadList )
		 {
			 try
			 {
				 t.join();
			 }
			 catch( Exception e)
			 {
				 e.printStackTrace();
			 }
			
		 }
		 
		 
		 for( ConcurrentStatement st : concurrentStatementList  )
		 {
			 st.addFailures();
		 }
		 
		 
		 
		 Statement after = createAfters( test);
		
		 evaluateStatement(after , eachTestNotifierList );
		
		
		
	    }
	 
	 
	 private boolean callIsIgnoredWithReflection(FrameworkMethod method)
	 {
		Method m;
		try {
			m = BlockJUnit4ClassRunner.class.getDeclaredMethod("isIgnored", FrameworkMethod.class);
		} catch (NoSuchMethodException e) {
		       return false;
		} catch (SecurityException e) {
			return false;
		}
		
		try {
			return ((Boolean) m.invoke(this, method)).booleanValue();
		} catch (IllegalAccessException e) {
		
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
		
			e.printStackTrace();
		}
		
		 return false;
		
		
	 }
	
	 
	 private void evaluateStatement(Statement statement ,List<EachTestNotifier>  eachTestNotifierList )
	 {
		 
		 TestResult testResult = ConcurrentStatement.evaluateStatement( statement);
		 
		 for( EachTestNotifier eachTestNotifier :   eachTestNotifierList )
		 {
			 testResult.addFailure(eachTestNotifier);
		 }
		 
		 
		 
	 }
	 
	 
	 private List<org.junit.rules.MethodRule> getMethodRules(Object target) {
	        return rules(target);
	    }
	 
	 private Statement withTestRules(FrameworkMethod method, List<TestRule> testRules,
	            Statement statement) {
	        return testRules.isEmpty() ? statement :
	                new RunRules(statement, testRules, describeChild(method));
	    }
	 
	 
	  private Statement withRules(FrameworkMethod method, Object target,
	            Statement statement) {
	        List<TestRule> testRules = getTestRules(target);
	        Statement result = statement;
	        result = withMethodRules(method, testRules, target, result);
	        result = withTestRules(method, testRules, result);

	        return result;
	    }

	    private Statement withMethodRules(FrameworkMethod method, List<TestRule> testRules,
	            Object target, Statement result) {
	        for (org.junit.rules.MethodRule each : getMethodRules(target)) {
	            if (!testRules.contains(each)) {
	                result = each.apply(result, method, target);
	            }
	        }
	        return result;
	    }

	 
	
   
    protected Statement createMethodStatement( FrameworkMethod method, Object test) {
       
     
            Statement statement;
            try {
            	statement = methodInvoker(method, test);
                statement = possiblyExpectingExceptions(method, test, statement);
                
                statement = withRules(method, test, statement);
            }
            catch (Throwable ex) {
                statement = new Fail(ex);
            }
            
            return statement;
        
    }

    
    
    
    
   
    protected Statement createBefores( Object target) {
        List<FrameworkMethod> befores = getTestClass().getAnnotatedMethods(
                Before.class);
        return befores.isEmpty() ? new NoOpStatement() : new InvokeListOfMethods(
                befores, target);
    }

   
    protected Statement createAfters(Object target) {
        List<FrameworkMethod> afters = getTestClass().getAnnotatedMethods(
                After.class);
        return afters.isEmpty() ? new NoOpStatement() : new InvokeListOfMethods( afters,
                target);
    }
    
    
    
    
    
    
    
    
    
    

	

}
