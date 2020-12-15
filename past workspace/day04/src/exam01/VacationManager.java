package exam01;

import java.util.Scanner;

public class VacationManager {
	public static void main(String[] args) {
		//입력: 이름, 근속 연수
		//출력:
		//홍길동님의 근속 연수는 ??년이고,
		//휴가 일수는 ??입니다.
		Scanner sc = new Scanner(System.in);
		String name;
		int yearsOfServices;
		int vacationDays;
		String message = "";
		
		System.out.print("이름: ");
		name = sc.next();
		
		System.out.print("근속 연수: ");
		yearsOfServices = sc.nextInt();
		
		if (yearsOfServices >= 15) {
			vacationDays = 50;
		} else if (yearsOfServices >= 9) {
			vacationDays = 30;
		} else if (yearsOfServices >= 7) {
			vacationDays = 20;
		} else if (yearsOfServices >= 4) {
			vacationDays = 10;
		} else if (yearsOfServices >= 1) {
			vacationDays = 5;
		} else {
			vacationDays = 0;
		}
		
		message += name + "님의 근속 연수는 " + yearsOfServices + "년이고,\n";
		message += "휴가 일수는 " + vacationDays + "일입니다.";
		
		System.out.println(message);
	}
}
