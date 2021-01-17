package com.newlecture.app.util;

public class GList<T> {
	// Field
	private Object[] nums;
	private int current;
	
	// Constructor
	public GList() {
		nums = new Object[3];
		current = 0;
	}

	public void add(T num) {
		nums[current] = num;
		current++;
		
	}

	public void clear() {
		// nums = new int[3];
		current = 0;
	}

	public int size() {
		return current;
	}

	public T get(int index) {
		if (current <= index) {
			throw new IndexOutOfBoundsException();
		}
		return (T)nums[index];
	}

}
