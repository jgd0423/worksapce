package com.newlecture.app.util;

public class IntList {
	// Field
	private int[] nums;
	private int current;
	
	// Constructor
	public IntList() {
		nums = new int[3];
		current = 0;
	}

	public void add(int num) {
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

	public int get(int index) {
		if (current <= index) {
			throw new IndexOutOfBoundsException();
		}
		return nums[index];
	}

}
