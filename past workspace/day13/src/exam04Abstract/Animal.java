package exam04Abstract;

// 클래스 내에 추상메소드가 하나라도 있으면
// 그 클래스는 추상클래스가 되어야한다.
// 추상클래스로 객체를 생성할 수 없다.

/* 추상클래스 :
 * - 실체 클래스의 설계 규격 - 객체 생성용이 아님
 * - 클래스들의 공통적인 필드와 메소드를 추출하여 선언한 클래스.
 * - 추상클래스를 이용하여 다형성을 구현할 수 있다.
 * 
 * 추상 메소드 :
 * - 추상 클래스에서만 선언할 수 있고, 메소드의 선언부만 있는 메소드.
 * - 자식 클래스에서 재정의되어 실행내용을 결정해야 함.
 */
public abstract class Animal {
	public Animal() {
		System.out.println("-- Animal() --");
	}
	
	public abstract void sound(); // 추상메소드
	
	public void display() {
		System.out.println("-- animal display() --");
	}
}
