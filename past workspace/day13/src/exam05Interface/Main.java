package exam05Interface;

public class Main {
	public static void main(String[] args) {
		Animal ani = new Dog();
		ani.sound();
		ani.disp();
		
		ani = new Cat();
		ani.sound();
		ani.disp();
		
		ani = new Pig();
		ani.sound();
		ani.disp();
	}
}
