package exam02;

public class Test {
	String fname;
	String lname;
	int age;

	public static void main(String[] args) {
		Test t1 = new Test();
		System.out.println(t1.fname);
		System.out.println(t1.lname);
		System.out.println(t1.age);

		t1.fname = "홍";
		t1.lname = "길동";
		t1.age = 19;

		System.out.println(t1.fname);
		System.out.println(t1.lname);
		System.out.println(t1.age);

		Test t2 = new Test();
		System.out.println(t2.fname);
		System.out.println(t2.lname);
		System.out.println(t2.age);
		
		t2.fname = "이";
		t2.lname = "성순";
		t2.age = 21;
		
		System.out.println(t2.fname);
		System.out.println(t2.lname);
		System.out.println(t2.age);
		
	}
}
