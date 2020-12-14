package exam02;
//Lambda
public class Example01 {
	public static void main(String[] args) {
		InterfaceA ia;
		
		// (매개변수) -> {실행문};
		ia = (x, y) -> {
			return x + y;
		};
		int k = ia.method01(3, 4);
		
		System.out.println(k);

	}
}
