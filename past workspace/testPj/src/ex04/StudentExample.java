package ex04;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentExample {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<>();
		
		while (true) {
			System.out.print("선택(1:조회, 2:등록, 기타:종료):");
			String menuSelect = sc.next();
			if (menuSelect.equals("1")) {
				String message = "이름\t국어\t영어\t수학\t총점\t평균\n" +
			                     "===============================================";
				System.out.println(message);
				
				for (Student student : students) {
					student.display();
				}
			} else if (menuSelect.equals("2")) {
				while (true) {
					System.out.print("이름: ");
					String name = sc.next();
					if (name.toLowerCase().equals("q")) {
						break;
					}
					
					System.out.print("국어: ");
					int kor = sc.nextInt();
					
					System.out.print("영어: ");
					int eng = sc.nextInt();
					
					System.out.print("수학: ");
					int mat = sc.nextInt();
					
					Student s = new Student(name, kor, eng, mat);
					students.add(s);
					
				}
			} else {
				break;
			}
			
		}
		
		System.out.println("-- 프로그램 종료 --");
	}
}
