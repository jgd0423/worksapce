package exam01;

import java.util.Scanner;

public class PrivacyArray {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] names = new String[5];
		int[] bornYears = new int[5];
		int[] ages = new int[5];
		
		for (int i = 0; i < 5; i++) {
			String name;
			int bornYear;
			int age;
			
			System.out.print("이름을 입력하세요: ");
			name = sc.next();
			
			System.out.print("출생년도를 입력하세요: ");
			bornYear = sc.nextInt();
			
			age = 2020 - bornYear + 1;
			
			names[i] = name;
			bornYears[i] = bornYear;
			ages[i] = age;
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.println("이름: " + names[i] + ", 출생년도: " + bornYears[i] + 
					           ", 나이: " + ages[i]);
		}
	}
}
