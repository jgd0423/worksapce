package exam01;

import java.util.Scanner;

public class CalcSum2 {
	static void calcSum(int num) {
		int res = 0;
		for (int i = 1; i <= num; i++) {
			res += i;
		}

		System.out.println("1부터 " + num + "까지의 합계: " + res);
	}

	static int calcSumRecur(int num) {
		if (num == 1) {
			return 1;
		}
		return num + calcSumRecur(num - 1);
	}

	static void formatter(int num) {
		int res = calcSumRecur(num);
		System.out.println("1부터 " + num + "까지의 합계: " + res);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("숫자를 입력하세요: ");
		int num = sc.nextInt();

		calcSum(num);

		int calcSumRecurResult = calcSumRecur(num);
		formatter(num);
	}
}
