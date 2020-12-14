package exam01;

import java.util.Scanner;

public class LeapYear {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int startYear;
		int endYear;
		
		System.out.print("시작 연도: ");
		startYear = sc.nextInt();
		
		System.out.print("마지막 연도: ");
		endYear = sc.nextInt();

		for (int year = startYear; year <= endYear; year++) {
			if ((year % 400 == 0) || (year % 4 == 0) && ((year % 100 != 0))) {
				System.out.println(year + "년은 윤년입니다.");
			} else {
				System.out.println(year + "년은 평년입니다.");
			}
		}
	}
}
