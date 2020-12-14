package exam01;

public class PolymorphismTest01 {
	public static void main(String[] args) {
//		Animal animal = new Animal();
//		animal.sound();
//		
//		Dog dog = new Dog();
//		dog.sound();
//		
//		Cat cat = new Cat();
//		cat.sound();
//		
//		Pig pig = new Pig();
//		pig.sound();
		
//		Animal ani = new Animal();
//		Animal dog = new Dog(); // 자동형변환
//		dog.dispA();
//		dog.sound();
		
		// 다형성은 부모타입의 변수로 자식객체를 만드는 것.
		// 자식객체는 부모타입으로 형변환된다.
		// 생성된 객체는 부모의 맴버를 사용하게 된다.
		// 예외로 ovrriding된 메소드는 자식 메소드를 사용한다.
		// 상속, 형변환, 오버라이딩(재정의)
		
		Animal ani = new Animal();
		ani.dispA();
		ani.sound();
		
		Dog dog = new Dog();
		ani = dog;
		ani.dispA();
		ani.sound();
		
		ani = new Cat();
		ani.dispA();
		ani.sound();
		
		ani = new Pig();
		ani.dispA();
		ani.sound();
	}
}
