package exam07;

class Test222 {
	// Field
	static int a;
	
	// Constructor
	public Test222(int a) {
		this.a = a;
	}
	
	// Method
	public void disp() {
		System.out.println("a: " + a);
	}
}

public class Test111 {
	public static void main(String[] args) {
		Test222 t1 = new Test222(5);
		t1.disp();
		
		Test222 t2 = new Test222(7);
		t2.disp();
		
		t1.disp();
		
		
	}
}
