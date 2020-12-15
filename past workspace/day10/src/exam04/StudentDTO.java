package exam04;

import java.util.Scanner;

public class StudentDTO {
	// 입력: 학번, 이름, 학년, 평점
	// 출력: 학번, 이름, 학년, 평점, 장학금
	// 장학금 조건:
	// 3.0 이상: 50만원
	// 3.5 이상: 100만원
	// 4.0 이상: 150만원
	// 4.5 이상: 전액

	// Field
	private final int SCHOOL_EXPENSES = 2000000;
	private String studentId;
	private String name;
	private String studentGrade;
	private double rating;
	private int scholarship;

	// Constructor
	public StudentDTO() {
		input();
	}

	// Method
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("학번: ");
		studentId = sc.next();

		System.out.print("이름: ");
		name = sc.next();

		System.out.print("학년: ");
		studentGrade = sc.next();

		System.out.print("평점: ");
		rating = sc.nextDouble();

		scholarship = calcScholarship();

	}

	public int calcScholarship() {
		int scholarship;
		if (rating >= 4.5) {
			scholarship = SCHOOL_EXPENSES;
		} else if (rating >= 4.0) {
			scholarship = 1500000;
		} else if (rating >= 3.5) {
			scholarship = 1000000;
		} else if (rating >= 3.0) {
			scholarship = 500000;
		} else {
			scholarship = 0;
		}

		return scholarship;
	}

	public void display() {
		String msg = "";

		msg += "학번\t\t이름\t학년\t평점\t장학금\n";
		msg += studentId + "\t" + name + "\t" + studentGrade + "\t" + rating + "\t" + scholarshipConvertStr();
		System.out.println(msg);
	}

	public String scholarshipConvertStr() {
		String scholarshipConvertStr = Integer.toString(scholarship);
		String str = "";
		if (scholarshipConvertStr.length() == 1) {
			str = "없음";
		} else {
			str = scholarshipConvertStr.substring(0, scholarshipConvertStr.length() - 4) + "만원";
		}
		return str;
	}

	@Override
	public String toString() {
		return "StudentDTO [SCHOOL_EXPENSES=" + SCHOOL_EXPENSES + ", studentId=" + studentId + ", name=" + name
				+ ", studentGrade=" + studentGrade + ", scholarship=" + scholarship + "]";
	}

	// Getter, Setter

}
