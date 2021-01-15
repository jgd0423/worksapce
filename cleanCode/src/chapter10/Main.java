package chapter10;

import java.util.Optional;


public class Main {
	
	public static int sumRecursion(int num) {
		if (num == 1) {
			return 1;
		}
		
		return num + sumRecursion(num - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(sumRecursion(5));
		
	}

}
