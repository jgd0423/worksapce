package exam04;

/**
 * 객체지향 프로그램의 4대 특성
 * 캡슐화 : 정보의 은닉화, Getter & Setter
 * 상속 : 부모의 맴버를 물려받는 것. + 재정의 (Overriding)
 * 추상화
 * 다형성
 */

public class Example {
	public static void main(String[] args) {
		Dog d1 = new Dog("방울이", 3); 
		d1.sound();
		d1.display();
		
		Cat c1 = new Cat("무", 2);
		c1.sound();
		c1.display();
		
		Bird b1 = new Bird("후투티", 6);
		b1.sound();
		b1.display();
	}
}
