package exam01;

import java.util.Scanner;

public class Test05 {
	public static void main(String[] args) {
		//입력: 이름, 국어, 영어, 수학
		//출력:
		/*
		 =====================================
		  이름  국어    영어     수학     총점  평균 
		 =====================================
		 홍길동  90  90  90  270 90
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		String name;
		int korean;
		int english;
		int math;
		int totalScore;
		double averageScore;
		String grade;
		String message = "";
		
		System.out.print("이름: ");
		name = scanner.next();
		System.out.print("국어 점수: ");
		korean = scanner.nextInt();
		System.out.print("영어 점수: ");
		english = scanner.nextInt();
		System.out.print("수학 점수: ");
		math = scanner.nextInt();
		
		totalScore = korean + english + math;
		averageScore = (double) totalScore / 3;
		
//		if (averageScore >= 90) {
//			grade = "수";
//		} else if (averageScore >= 80) {
//			grade = "우";
//		} else if (averageScore >= 70) {
//			grade = "미";
//		} else if (averageScore >= 60) {
//			grade = "양";
//		} else {
//			grade = "가";
//		}
		
		switch ((int)(averageScore / 10)) {
		case 10:
		case 9:
			grade = "수";
			break;
		case 8:
			grade = "우";
			break;
		case 7:
			grade = "미";
			break;
		case 6:
			grade = "양";
			break;
		default:
			grade = "가";
			break;
		}
		
		message += "=====================================================\n";
		message += "이름\t국어\t영어\t수학\t총점\t평균\t등급\n";
		message += "=====================================================\n";
		message += name + "\t" + korean + "\t" + english + "\t" + 
		           math + "\t" + totalScore + "\t" + String.format("%.2f", averageScore)  + "\t" + grade + "\n";
		message += "=====================================================";
		
		System.out.println(message);
	}
}
