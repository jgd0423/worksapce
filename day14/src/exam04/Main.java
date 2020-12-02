package exam04;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		String path = "D:\\jgd\\attach\\test.txt";
		ReadFile rf = new ReadFile(path);
		ArrayList<String[]> studentInfos = rf.studentInfos;
//		ArrayList<Student> students = new ArrayList<Student>();
		Student[] students = new Student[10];
		
		for (int i = 0; i < studentInfos.size(); i++) {
			String[] student = studentInfos.get(i);
			String name = student[0];
			int kor = Integer.parseInt(student[1]);
			int eng = Integer.parseInt(student[2]);
			int mat = Integer.parseInt(student[3]);
			int sci = Integer.parseInt(student[4]);
			int his = Integer.parseInt(student[5]);
			
			Student s = new Student(name, kor, eng, mat, sci, his);
			students[i] = s;
		}
		
		System.out.println("이름\t국어\t영어\t수학\t과학\t역사\t총점\t평균");
		for (Student s : students) {
			s.display();
		}
	}
}
