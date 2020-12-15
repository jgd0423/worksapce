package exam01;

public class TwoDimensionPrac2 {
	public static void main(String[] args) {
		int[][] array = new int[2][3];
		int k = 10;

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = k;
				k += 10;
			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println(array[i][j]);
			}
		}

	}
}
