package exam01;

public class Test01 {
	public static void main(String[] args) {
		String temp = "aa|10|10|10|30|10.0|가";
		String[] temps = temp.split("\\|");

//		for (int i = 0; i<temps.length; i++) {
//			System.out.println(temps[i]);
		System.out.println(temps.length);
//		}
		
	}
}
