package exam01;

public class ArrayStringTest {
	public static void main(String[] args) {
		String[] str1 = { "값1", "값2", "값3" };
		String temp = str1[0];
		System.out.println(temp);
		System.out.println(str1.length);

		for (int i = 0; i < str1.length; i++) {
			System.out.println(str1[i]);
		}
	}
}
