package exam05Interface;

// 자바에서 추상화를 달성하는 또 다른 방법은 인터페이스를 사용하는 것이다.
// 인터페이스는 객체를 만들 수 없다. -> 생성자 필요없음
// 상수, 추상메소드 (인터페이스 구성요소)
public interface Animal {
	public void sound();
	public void disp();
}
