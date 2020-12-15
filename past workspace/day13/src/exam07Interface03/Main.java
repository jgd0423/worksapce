package exam07Interface03;

public class Main {
	public static void main(String[] args) {
		Face01 testImpl = new TestImpl();
		testImpl.method1();
		
		TestImpl testImpl2 = new TestImpl();
		testImpl2.method1();
		testImpl2.method2();
	}
}
