package part3.ex2.메소드;

public class ExamList {
	private Exam[] exams;
	private int current;

	public ExamList() {
		this(3);
	}
	
	// Aggregation Has A : ExamList객체가 만들어 질 때 Exam객체는 만들어지지 않는다.
	public ExamList(int size) {
		exams = new Exam[size];
		current = 0;
	}
    
	public Exam get(int i) {
		return this.exams[i];
	}

	public void add(Exam exam) {
        int size = current;
        
        if (exams.length == size) {
        	Exam[] temp = new Exam[size + 5];
        	for (int i = 0; i < size; i++) {
        		temp[i] = exams[i];
        	}
        	exams = temp;
        }
        
        exams[current] = exam;
        current++;
	}


	public int size() {
		return current;
	}
}
