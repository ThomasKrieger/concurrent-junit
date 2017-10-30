package com.anarsoft.vmlens.concurrent.example;

public class AtomicPositiveValue {
   private int value;
   public AtomicPositiveValue(int newValue) throws Exception 
   {
	   if( newValue < 0 ) 
	   {
		   throw new Exception("value is negative");
	   }
	this.value = newValue;
   }
   public synchronized int get()
   {
	return value;
   }
   public synchronized void set(int newValue) throws Exception
   {
	   if( newValue < 0 ) 
	   {
		   throw new Exception("value is negative");
	   }
	   value = newValue;
   }
   public synchronized int update(int delta) throws Exception
   {
	   int temp = value + delta;
	   if( temp < 0 ) 
	   {
		   throw new Exception("value is negative");
	   }
	   value = temp;
	   return value;   
   }
   public synchronized void transfer(AtomicPositiveValue other, int amount) throws Exception
   {
	   other.update( -1 * amount );
	   update(amount); 
   }
}
