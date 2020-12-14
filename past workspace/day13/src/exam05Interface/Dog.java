package exam05Interface;

// 인터페이스를 구현하는 구현 클래스
// Animal 인터페이스를 구현함
public class Dog implements Animal {

	@Override
	public void sound() {
		System.out.println("-- 멍멍 --");
	}

	@Override
	public void disp() {
		System.out.println("-- 개 --");
	}

}
