package ex04;

public class Student {
	// Field
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private double avg;
	
	// Constructor
	public Student() {}

	public Student(String name, int kor, int eng, int mat) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = calcTot();
		this.avg = calcAvg();
	}

	// Method
	public int calcTot() {
		int tot = kor + eng + mat;
		return tot;
	}
	
	public double calcAvg() {
		double avg = (double) tot / 3;
		return avg;
	}
	
	public void display() {
		String message = "";
		message += name + "\t";
		message += kor + "\t";
		message += eng + "\t";
		message += mat + "\t";
		message += tot + "\t";
		message += String.format("%.2f", avg);
		
		System.out.println(message);
	}
	
	// Getters & Setters
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
