package exam01;

public class Test03 {
	// Field
	
	// Constructor
	public Test03() {}
	
	// Method
	public int method1(int i) {
		int result = i + 5;
		return result;
	}
	
	public int method2(int i, int j) {
		int result = i + j;
		return result;
	}
	
	public static void main(String[] args) {
		Test03 t1 = new Test03();
		int k = t1.method1(10);
		System.out.println(k);
		
		int result2 = t1.method2(4, 5);
		System.out.println(result2);
	}
}
