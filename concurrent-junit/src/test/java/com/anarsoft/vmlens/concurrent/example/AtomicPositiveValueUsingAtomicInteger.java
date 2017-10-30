package com.anarsoft.vmlens.concurrent.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPositiveValueUsingAtomicInteger {
	private final AtomicInteger value;
	public AtomicPositiveValueUsingAtomicInteger(int newValue) throws Exception {
		if (newValue < 0) {
			throw new Exception("value is negative");
		}
		value = new AtomicInteger(newValue);
	}

	public int get() {
		return value.get();
	}

	public int update(int delta) throws Exception {
		int current = value.get();
		int update = current + delta;
		if (update < 0) {
			throw new Exception("value is negative");
		}
		while (!value.compareAndSet(current, update)) {
			update = current + delta;
			if (update < 0) {
				throw new Exception("value negative");
			}
		}
		return update;
	}
}
