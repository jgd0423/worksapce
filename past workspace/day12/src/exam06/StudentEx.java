package exam06;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentEx {
	public static ArrayList<Student> inputStudent() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<Student>();
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
			
			System.out.print("과학: ");
			int sci = sc.nextInt();
			
			System.out.print("역사: ");
			int his = sc.nextInt();
			
			Student s1 = new Student(name, kor, eng, mat, sci, his);
			students.add(s1);
		}
		
		return students;
	}
	
	public static void displayStudent(ArrayList<Student> students) {
		String message = "이름\t국어\t영어\t수학\t과학\t역사\t총점\t평균\t등급";
		System.out.println(message);
		for (int i = 0; i < students.size(); i++) {
			students.get(i).display();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Student> students = inputStudent();
		displayStudent(students);
	}
}
