package exam01;

public class Example01 {
	public static void main(String[] args) {
		InterfaceA ia = new InterfaceAImpl(); // 자동형변환
		ia.method01();
	}
}
