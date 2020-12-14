package exam01;

import java.util.Scanner;

//바이트코드 .class file

class Temp {
	Scanner sc = new Scanner(System.in);
	int kor;
	int eng;
	int mat;
	int tot;
	double avg;

	// 생성자, Constructor
	public Temp(int k, int e, int m) {
		kor = k;
		eng = e;
		mat = m;
		tot = k + e + m;
		avg = (double) tot / 3;
	}
	// 생성자 오버로딩
	public Temp() {

	}

	// 메소드, Method
	public void prn() {
		System.out.println("----------------------------------------");
		System.out.println("국어\t영어\t수학\t총점\t평균");
		System.out.println("----------------------------------------");
		System.out.println(kor + " \t" + eng + "\t" + mat + "\t" + tot + "\t" + String.format("%.2f", avg));
		System.out.println("----------------------------------------");
	}
}

public class Test01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("국어: ");
		int kor = sc.nextInt();
		System.out.print("영어: ");
		int eng = sc.nextInt();
		System.out.print("수학: ");
		int mat = sc.nextInt();

		Temp t1 = new Temp(kor, eng, mat);
		t1.prn();

		System.out.println("--프로그램 종료--");

	}
}
