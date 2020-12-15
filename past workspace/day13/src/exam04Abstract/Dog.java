package exam04Abstract;

public class Dog extends Animal {
	public Dog() {
		System.out.println("-- Dog() --");
	}

	@Override // 어노테이션. 오버라이딩이 잘 되었는지  컴파일러가  확인하게 하려고 작성함.
	public void sound() {
		System.out.println("-- 멍멍 --");
	}

}
