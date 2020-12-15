package exam02;

public class PointDTO {
	// Field
	// 이름, 국어, 영어, 수학, 총점, 평균, 등급
	String name;
	int kor;
	int eng;
	int mat;
	int tot;
	double avg;
	String grade;
	
	// Constructor
	public PointDTO() {}
	
	// Method
	public void tot() {
		tot = kor + eng + mat;
	}
	
	public void avg() {
		avg = (double) tot / 3;
	}
	
	public void grade() {
		if (avg >= 90) {
			grade = "수";
		} else if (avg >= 80) {
			grade = "우";
		} else if (avg >= 70) {
			grade = "미";
		} else if (avg >= 60) {
			grade = "양";
		} else {
			grade = "가";
		}
	}

	@Override
	public String toString() {
		return "PointDTO [name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot=" + tot + ", avg="
				+ avg + ", grade=" + grade + "]";
	}
	
	
}
