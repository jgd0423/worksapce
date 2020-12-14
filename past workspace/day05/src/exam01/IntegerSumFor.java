package exam01;

import java.util.Scanner;

public class IntegerSumFor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요 : ");
		int inputNum = sc.nextInt();
		int evenSum = 0;
		int oddSum = 0;
		int totalSum = 0;
		String msg = "";
		
		for (int tempNum = 1; tempNum <= inputNum; tempNum++) {
			if (tempNum % 2 == 0) {
				evenSum += tempNum;
			} else {
				oddSum += tempNum;
			}
		}
		
		totalSum = evenSum + oddSum;
		
		msg += "1 ~ " + inputNum + "까지의 짝수의 합계 : " + evenSum + "\n";
		msg += "1 ~ " + inputNum + "까지의 홀수의 합계 : " + oddSum + "\n";
		msg += "1 ~ " + inputNum + "까지의 합계 : " + totalSum;
		
		System.out.println(msg);
	}
}
