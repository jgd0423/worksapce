package exam01;

import java.util.Scanner;

public class Test04 {
	public static void main(String[] args) {
		//입력: 이름, 주민번호
		//출력: 이름, 주빈번호, 성별, 나이
		Scanner scanner = new Scanner(System.in);
		final int THISYEAR = 2020;
		String name;
		String personalNumber;
		String genderNumber;
		String gender;
		int birthYear; 
		int age;
		String message = "";
		
		System.out.print("이름: ");
		name = scanner.next();
		System.out.print("주민번호: ");
		personalNumber = scanner.next();
		genderNumber = personalNumber.substring(6, 7);
		gender = (genderNumber.equals("1") || genderNumber.equals("3")) ? "남자" : "여자";
		
		if (genderNumber.equals("1") || genderNumber.equals("2")) {
			String tempYear = "19" + personalNumber.substring(0, 2);
			birthYear = Integer.parseInt(tempYear);
		} else {
			String tempYear = "20" + personalNumber.substring(0, 2);
			birthYear = Integer.parseInt(tempYear);
		}
		
		age = THISYEAR - birthYear + 1;
		
		message += "이름\t주민번호\t\t성별\t나이\n";
		message += name + "\t" + personalNumber + "\t" + gender + "\t" + age;
		
		System.out.println(message);

	}
}
