package com.anarsoft.vmlens.concurrent.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
@RunWith(ConcurrentTestRunner.class)
public class TestConcurrentHashMapCompute {
	private final ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<Integer,Integer>();
	public TestConcurrentHashMapCompute()
	{
		map.put(1, 1);
		map.put(2, 2);	
	}
	@Test
	public void update12()
	{
		map.compute( 1 ,   			
				new BiFunction<Integer,Integer,Integer>()
				{
					public Integer apply(Integer k, Integer v) {		
						map.put( 2 , 1);
						return v;
					}
				}
				);
	}
	@Test
	public void update21()
	{
              map.compute( 2 ,   			
				new BiFunction<Integer,Integer,Integer>()
				{
					public Integer apply(Integer k, Integer v) {		
						map.put( 1 , 1);
						return v;
					}
				}
				);
	}
}
