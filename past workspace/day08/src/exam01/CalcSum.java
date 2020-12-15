package exam01;

import java.util.Scanner;

public class CalcSum {
	// 메소드는 같은 레벨에서만 선언 가능하다. 클래스 바로 안쪽
	static void calcSum(int a, int b) {
		int sumResult = a + b;
		System.out.println(sumResult);
	}

	static int calcSum2(int a, int b) {
		int sumResult = a + b;
		return sumResult;
	}

	static String calcSum3() {
		int num1 = 5;
		int num2 = 9;
		int hab = num1 + num2;
		String temp = num1 + "|" + num2 + "|" + hab;
		return temp;
	}

	public static void main(String[] args) {
		String temp = calcSum3();
		String[] temp_ = temp.split("\\|");
		int a = Integer.parseInt(temp_[0]);
		int b = Integer.parseInt(temp_[1]);
		int hab = Integer.parseInt(temp_[2]);
		System.out.println(a);
		System.out.println(b);
		System.out.println(hab);

		System.out.println("-- 프로그램 종료 --");
	}
}
