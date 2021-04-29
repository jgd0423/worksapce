public class Program {
	
	// 재사용은 소스코드를 복붙하는게 아니라 바이너리를 재사용하는 것이다.
	// STructuredJavaPrj의 part3.ex2.메소드 부분의 
	// Exam.java, ExamConsole.java, ExamList.java를 jar로 Export한다.
	// OOPJavaPrj에 build path에서 jar를 등록한다.
	
	public static void main(String[] args) {
		NewlecExam exam = new NewlecExam(1, 1, 1, 1);
		
		System.out.println(exam.total());
		System.out.println(exam.avg());
	}
}
