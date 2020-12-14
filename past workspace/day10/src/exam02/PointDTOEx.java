package exam02;

import java.util.Scanner;

public class PointDTOEx {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("이름: ");
		String name = sc.next();

		System.out.print("국어: ");
		int kor = sc.nextInt();

		System.out.print("영어: ");
		int eng = sc.nextInt();

		System.out.print("수학: ");
		int mat = sc.nextInt();
		
		PointDTO p1 = new PointDTO();
		p1.name = name;
		p1.kor = kor;
		p1.eng = eng;
		p1.mat = mat;
		p1.tot();
		p1.avg();
		p1.grade();

		System.out.println(p1.toString());
	}
}
