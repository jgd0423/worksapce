package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class GradeManager2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] temps = new String[1];

		String message = "";
		message += "--------------------------------------------------------\n";
		message += "이름\t국어\t영어\t수학\t총점\t평균\t등급\n";
		message += "--------------------------------------------------------\n";

		for (int i = 0; i < 1; i++) {
			String grade = "가";

			System.out.print("이름: ");
			String name = sc.next();

			System.out.print("국어: ");
			int koreanScore = sc.nextInt();

			System.out.print("영어: ");
			int englishScore = sc.nextInt();

			System.out.print("수학: ");
			int mathScore = sc.nextInt();

			int totalScore = koreanScore + englishScore + mathScore;
			double average = (double) totalScore / 3;

			if (average >= 90) {
				grade = "수";
			} else if (average >= 80) {
				grade = "우";
			} else if (average >= 70) {
				grade = "미";
			} else if (average >= 60) {
				grade = "양";
			}

			String temp = "";
			temp += name + "|";
			temp += koreanScore + "|";
			temp += englishScore + "|";
			temp += mathScore + "|";
			temp += totalScore + "|";
			temp += average + "|";
			temp += grade;
			
			temps[i] = temp;
		}


		message += "--------------------------------------------------------";
		
		System.out.println(Arrays.toString(temps));
		System.out.println(Arrays.toString(temps));
		String[] aaa = temps[0].split("\\|"); // [|]
		System.out.println(Arrays.deepToString(aaa));
	}
}
