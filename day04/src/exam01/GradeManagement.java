package exam01;

import java.util.Scanner;

public class GradeManagement {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name;
		int korean;
		int english;
		int math;
		int totalScore;
		double average;
		String grade;
		String msg = "";
		
		msg += "====================================================\n";
		msg += "이름\t국어\t영어\t수학\t총점\t평균\t등급\n";
		msg += "====================================================\n";
		
		for (int i = 0; i < 3; i++) {
			System.out.print("이름: ");
			name = sc.next();
			
			if (name.toUpperCase().equals("Q")) {
				break;
			}
			
			System.out.print("국어 점수: ");
			korean = sc.nextInt();
			
			System.out.print("영어 점수: ");
			english = sc.nextInt();
			
			System.out.print("수학 점수: ");
			math = sc.nextInt();
			
			totalScore = korean + english + math;
			average = (double) totalScore / 3;
			
			if (average >= 90) {
				grade = "수";
			} else if (average >= 80) {
				grade = "우";
			} else if (average >= 70) {
				grade = "미";
			} else if (average >= 60) {
				grade = "양";
			} else {
				grade = "가";
			}
			
			msg += name + "\t" + korean + "\t" + english + "\t" + math + "\t" + 
			       totalScore + "\t" + String.format("%.2f", average) + "\t" + grade + "\n" ;   
		}
		
		System.out.println(msg);
	}
}
