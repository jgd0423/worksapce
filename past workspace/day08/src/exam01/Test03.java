package exam01;

public class Test03 {
	static void prn(String[] n) {
		n[2] = "박철순";
		for (int i = 0; i < n.length; i++) {
			System.out.println(n[i]);
		}
	}

	public static void main(String[] args) {
		String[] names = { "홍길동", "이성순", "장천용", "이상대" };
		System.out.println(names);
		
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
		System.out.println("--------------------");
		prn(names);
	
	}
}
