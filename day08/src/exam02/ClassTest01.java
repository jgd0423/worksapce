package exam02;

public class ClassTest01 {
	//Member Field (필드) - 클래스에 포함된 변수
	int x = 10;
	int y = 20;
	//생성자
	//메소드
	
	public static void main(String[] args) {
		//클래스는 무조건 객체를 생성하고 사용해야한다.
		//자료형 변수 = new 생성자(); 
		//생성자는 클래스 이름과 동일한 메소드.
		
		ClassTest01 classTest01 = new ClassTest01(); // ClassTest01()은 클래스가 아니라 메소드다
		System.out.println(classTest01.x);
		System.out.println(classTest01.y);
		
		classTest01.x = 100;
		System.out.println(classTest01.x);
		
		ClassTest01 classTest02 = new ClassTest01();
		System.out.println(classTest02.x);
		System.out.println(classTest02.y);
	}
}
