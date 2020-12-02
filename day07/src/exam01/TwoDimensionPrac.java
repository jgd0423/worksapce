package exam01;

public class TwoDimensionPrac {
	public static void main(String[] args) {
		int[][] twoDimension = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		for (int i = 0; i < twoDimension.length; i++) {
			for (int j = 0; j < twoDimension[i].length; j++) {
				System.out.println(i + "행 " + j + "열의 값은 " + twoDimension[i][j] + "입니다.");
			}
			System.out.println();
		}
	}
}
