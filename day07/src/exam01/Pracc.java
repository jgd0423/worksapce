package exam01;

import java.util.ArrayList;
import java.util.Scanner;

public class Pracc {
	public static void main(String[] args) {
		// 입력: 국어, 영어, 수학, 과학, 역사, 체육, 음악, 미술
		// 출력: 국어, 영어, 수학, 과학, 역사, 체육, 음악, 미술, 총점, 평균, 등급
		Scanner sc = new Scanner(System.in);
		ArrayList classes = new ArrayList();
		String[] ments = { "국어", "영어", "수학", "과학", "역사", "체육", "음악", "미술" };
		int totalScore = 0;
		double average;
		String grade = "가";
		String message = "";

		for (int i = 0; i < ments.length; i++) {
			System.out.print(ments[i] + ": ");
			int temp = sc.nextInt();
			classes.add(temp);
		}

		for (int i = 0; i < classes.size(); i++) {
			totalScore += (int) classes.get(i);
		}

		average = (double) totalScore / classes.size();

		if (average >= 90) {
			grade = "수";
		} else if (average >= 80) {
			grade = "우";
		} else if (average >= 70) {
			grade = "미";
		} else if (average >= 60) {
			grade = "양";
		}

		classes.add(totalScore);
		classes.add(average);
		classes.add(grade);

		message += "국어\t영어\t수학\t과학\t역사\t체육\t"
				+ "음악\t미술\t총점\t평균\t등급\n";

		for (int i = 0; i < classes.size(); i++) {
			message += classes.get(i) + "\t";
		}

		System.out.println(message);
	}

}
