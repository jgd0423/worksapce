import part3.ex2.메소드.Exam;

public class NewlecExam extends Exam {
	private int com;
	
	@Override
	public int total() {
		return super.total() + com;
	}
	
	@Override
	public float avg() {
		return total() / 4.0f;
	}
	
	public int getCom() {
		return com;
	}

	public void setCom(int com) {
		this.com = com;
	}
}
