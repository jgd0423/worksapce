package exam03;

import java.util.Scanner;

public class PointDTO {
	// Field
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private double avg;

	// Constructor
	public PointDTO() {
		input();
		display();
	}
	
	// Method
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름: ");
		name = sc.next();
		System.out.print("국어: ");
		kor = sc.nextInt();
		System.out.print("영어: ");
		eng = sc.nextInt();
		System.out.print("수학: ");
		mat = sc.nextInt();
		
		tot = kor + eng + mat;
		avg = (double) tot / 3;
	}
	
	public void display() {
		String msg = "";
		msg += name + "\t";
		msg += kor + "\t";
		msg += eng + "\t";
		msg += mat + "\t";
		msg += tot + "\t";
		msg += avg + "\n";
		System.out.println(msg);
	}
	
	
	// toString
	@Override
	public String toString() {
		return "PointDTO [name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot=" + tot + ", avg="
				+ avg + "]";
	}

	// Getter, Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

}
