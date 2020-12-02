package exam01;

import java.util.Scanner;

public class Test02 {
	public static void main(String[] args) {
		//입력 : 이름, 출생년도
		//출력 : 이름, 나이
		Scanner scanner = new Scanner(System.in);		
		final int THISYEAR = 2020;
		int age;
		String msg = "";
		
		System.out.print("이름을 입력하세요: ");
		String name = scanner.next();
		scanner.nextLine();
		
		System.out.print("출생년도를 입력하세요: ");
		int bornYear = scanner.nextInt();
		scanner.nextLine();
		
		scanner.close();
		
		age = THISYEAR - bornYear + 1;

		msg += "이름\t나이\n";
		msg += name + "\t" + age;
		System.out.println(msg);
		
		
		
	}
}
