package exam01;

public class Test02 {
	// Field
	String str;
	int num;
	
	// Constructor
	public Test02() {}
	
	// Method
	void method1() {
		System.out.println("-- method1 --");
	}
	
	void method1(String name) {
		System.out.println(name + "님, 안녕하세요?");
	}
	
	void method1(String name, int age) {
		System.out.println("이름: " + name + ", 나이: " + age);
	}

	public static void main(String[] args) {
		Test02 t1 = new Test02();
		t1.method1();
		t1.method1();
		t1.method1("홍길동");
		t1.method1("홍", 20);
	}
}
