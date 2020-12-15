package exam01;

import java.util.Scanner;

public class MethodPracc2 {
	//멤버 필드
	//멤버 메소드
	static void calc(int a, int b) {
		System.out.println(a + " + " + b + " = " + (a + b));
		System.out.println(a + " - " + b + " = " + (a - b));
		System.out.println(a + " * " + b + " = " + (a * b));
		System.out.println(a + " / " + b + " = " + ((double) a / b));
		System.out.println(a + " % " + b + " = " + (a % b));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("첫 번째 숫자: ");
			int a = sc.nextInt();
			
			if (a == 0) {
				break;
			}
			
			System.out.print("두 번째 숫자: ");
			int b = sc.nextInt();
			
			calc(a, b);
			
		}
		System.out.println("--프로그램 종료--");
	}
}
