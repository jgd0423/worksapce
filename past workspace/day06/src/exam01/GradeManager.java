package exam01;

import java.util.Scanner;

public class GradeManager {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] names = new String[5];
		int[] koreanScores = new int[5];
		int[] englishScores = new int[5];
		int[] mathScores = new int[5];
		int[] totalScores = new int[5];
		double[] averages = new double[5];
		String[] grades = new String[5];
		String message = "";
		message += "--------------------------------------------------------\n";
		message += "이름\t국어\t영어\t수학\t총점\t평균\t등급\n";
		message += "--------------------------------------------------------\n";

		for (int i = 0; i < 5; i++) {
			String grade;

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
			} else {
				grade = "가";
			}

			names[i] = name;
			koreanScores[i] = koreanScore;
			englishScores[i] = englishScore;
			mathScores[i] = mathScore;
			totalScores[i] = totalScore;
			averages[i] = average;
			grades[i] = grade;
		}

		for (int i = 0; i < 5; i++) {
			message += names[i] + "\t" + koreanScores[i] + "\t" + englishScores[i] + "\t" + mathScores[i] + "\t"
					+ totalScores[i] + "\t" + String.format("%.2f", averages[i]) + "\t" + grades[i] + "\n";
		}

		message += "--------------------------------------------------------";

		System.out.println(message);
	}
}
