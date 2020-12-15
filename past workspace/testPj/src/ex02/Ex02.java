package ex02;

import java.util.Scanner;

public class Ex02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num;
		int res = 0;
		
		System.out.print("숫자를 입력하세요: ");
		num = sc.nextInt();
		
		for (int i = 1; i <= num; i++) {
			res += i;
		}
		
		System.out.println(res);
	}
}
