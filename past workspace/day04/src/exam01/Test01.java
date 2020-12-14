package exam01;

import java.util.Scanner;

public class Test01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year = 1304;
		
//		System.out.print("연도를 입력하세요: ");
//		year = sc.nextInt();
		
		if ((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0))) {
			System.out.println("윤년");
		} else {
			System.out.println("평년");
		}

	}
	
}
