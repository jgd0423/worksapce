package exam01;

import java.util.ArrayList;
import java.util.Arrays;

public class Test02 {
	static void test(int a, int b, int c) {
		a = 100;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
	}

	public static void main(String[] args) {
		int[] nums = { 10, 20, 30 };
		test(nums[0], nums[1], nums[2]);

		for (int i = 0; i < nums.length; i++) {
			System.out.println("-> " + nums[i]);
		}
	}
}
