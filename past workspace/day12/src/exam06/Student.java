package exam06;

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
	private String grade;

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
		this.grade = calcGrade();
	}

	// Method
	public int calcTot() {
		int tot = kor + eng + mat + sci + his;
		return tot;
	}

	public double calcAvg() {
		double avg = (double) tot / 5;
		return avg;
	}

	public String calcGrade() {
		String grade = "가";
		if (avg >= 90) {
			grade = "수";
		} else if (avg >= 80) {
			grade = "우";
		} else if (avg >= 70) {
			grade = "미";
		} else if (avg >= 60) {
			grade = "양";
		}

		return grade;
	}

	public void display() {
		String message = name + "\t" + kor + "\t" + eng + "\t" + 
	                     mat + "\t" + sci + "\t" + his + "\t" + 
				         tot + "\t" + String.format("%.2f", avg) + "\t" + grade;
		System.out.println(message);
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
