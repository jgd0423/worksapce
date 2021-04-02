package com.test.spring01.test;

public class Test05DTO {
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int sci;
	private int his;
	private int tot;
	private double avg;
	
	public Test05DTO() {}
	
	public void calcTot() {
		this.tot = this.kor + this.eng + this.mat + this.sci + this.his;
	}
	
	public void calcAvg() {
		this.avg = (double)tot / 5;
	}
	
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
	public int getSci() {
		return sci;
	}
	public void setSci(int sci) {
		this.sci = sci;
	}
	public int getHis() {
		return his;
	}
	public void setHis(int his) {
		this.his = his;
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
