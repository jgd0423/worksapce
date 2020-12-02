package exam04;

public class Student {
	// Field
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int sci;
	private int his;
	private int tot;
	private double avg;
	
	// Constructor
	public Student(String name, int kor, int eng, int mat, int sci, int his) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sci = sci;
		this.his = his;
		this.tot = calcTot();
		this.avg = calcAvg();
	}
	
	// Method
	public void display() {
		String msg = "";
		msg += name + "\t";
		msg += kor + "\t";
		msg += eng + "\t";
		msg += mat + "\t";
		msg += sci + "\t";
		msg += his + "\t";
		msg += tot + "\t";
		msg += avg + "\t";
		
		System.out.println(msg);
	}
	
	public int calcTot() {
		int tot = kor + eng + mat + sci + his;
		return tot;
	}
	
	public double calcAvg() {
		double avg = (double) tot / 5;
		return avg;
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
