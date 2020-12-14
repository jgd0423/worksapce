package exam06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student2 {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<HashMap<String, String>> students = new ArrayList<>();
	public static String name;
	public static int kor;
	public static int eng;
	public static int mat;
	public static int sci;
	public static int his;
	public static int tot;
	public static double avg;
	public static String grade;
	
	public static void inputStudent() {
		while (true) {
			HashMap<String, String> student = new HashMap<>();
			System.out.print("이름: ");
			name = sc.next();
			if (name.toLowerCase().equals("q")) {
				break;
			}
			
			System.out.print("국어: ");
			kor = sc.nextInt();
			
			System.out.print("영어: "	);
			eng = sc.nextInt();
			
			System.out.print("수학: ");
			mat = sc.nextInt();
			
			System.out.print("과학: ");
			sci = sc.nextInt();
			
			System.out.print("역사: ");
			his = sc.nextInt();
			
			tot = tot();
			avg = avg();
			grade = calcGrade();
			
			student.put("이름", name);
			student.put("국어", Integer.toString(kor));
			student.put("영어", Integer.toString(eng));
			student.put("수학", Integer.toString(mat));
			student.put("과학", Integer.toString(sci));
			student.put("역사", Integer.toString(his));
			student.put("총점", Integer.toString(tot));
			student.put("평균", Double.toString(avg));
			student.put("등급", grade);
			
			students.add(student);
		}	
	}
	
	public static int tot() {
		int tot = kor + eng + mat + sci + his;
		return tot;
	}
	
	public static double avg() {
		double avg = (double) tot / 5;
		return avg;
	}
	
	public static String calcGrade() {
		String grade = "가";
		if (avg >= 90) {
			grade = "수";
		} else if (avg >= 80) {
			grade = "우";
		} else if (avg >= 70) {
			grade = "미";
		} else if (avg >= 60) {
			grade = "양";
		}
		
		return grade;
	}
	
	public static void display() {
		String message = "이름\t국어\t영어\t수학\t과학\t역사\t총점\t평균\t등급";
		System.out.println(message);
		for (HashMap<String, String> student : students) {
			displayStudent(student);
		}
	}
	
	public static void displayStudent(HashMap<String, String> student) {
		String message = student.get("이름") + "\t" + 
				student.get("국어") + "\t" + 
				student.get("영어") + "\t" + 
				student.get("수학") + "\t" + 
				student.get("과학") + "\t" + 
				student.get("역사") + "\t" + 
				student.get("총점") + "\t" + 
				String.format("%.2f", student.get("평균")) + "\t" + 
				student.get("등급");
		System.out.println(message);
	}
	
	public static void main(String[] args) {
		inputStudent();
		display();
	}
}

