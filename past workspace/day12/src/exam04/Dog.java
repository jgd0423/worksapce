package exam04;

public class Dog extends Animal {
	public Dog(String name, int age) {
		super(name, age);
	}
	
	public void sound() {
		System.out.println("-- 멍멍 --");
	}
}
