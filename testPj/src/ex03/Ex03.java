package ex03;

import java.util.Scanner;

public class Ex03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] students = new String[3];
		String res = "이름\t국어\t영어\t수학\t총점\t평균\n" +
		             "===============================================\n";
		
		for (int i = 0; i < 3; i++) {
			String student = "";
			
			System.out.print("이름: ");
			String name = sc.next();
			student += name + "|";
			
			System.out.print("국어: ");
			String kor = sc.next();
			student += kor + "|";
			
			System.out.print("영어: ");
			String eng = sc.next();
			student += eng + "|";
			
			System.out.print("수학: ");
			String mat = sc.next();
			student += mat;
			
			students[i] = student;
		}
		
		for (String student : students) {
			String[] studentInfo = student.split("\\|");
			String name = studentInfo[0];
			int kor = Integer.parseInt(studentInfo[1]);
			int eng = Integer.parseInt(studentInfo[2]);
			int mat = Integer.parseInt(studentInfo[3]);
			int tot = kor + eng + mat;
			double avg_ = (double) tot / 3;
			String avg = String.format("%.2f", avg_);
			
			String message = "";
			message += name + "\t";
			message += kor + "\t";
			message += eng + "\t";
			message += mat + "\t";
			message += tot + "\t";
			message += avg + "\n";
			
			res += message;
		}
		
		System.out.println(res);
	}
}
