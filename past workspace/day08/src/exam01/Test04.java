package exam01;

public class Test04 {
	static void prn(String... n) {
		for (int i = 0; i < n.length; i++) {
			System.out.println("n[" + i + "] : " + n[i]);
		}
	}

	public static void main(String[] args) {
		prn("홍길동", "19");
		prn("100", "200", "300", "400");

	}
}
