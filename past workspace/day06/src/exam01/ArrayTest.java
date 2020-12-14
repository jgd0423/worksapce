package exam01;

import java.util.Arrays;

public class ArrayTest {
	public static void main(String[] args) {
		// 배열 선언 및 초기화 방법
		int[] array1 = new int[5];
		int[] array2 = { 1, 2, 3, 4, 5 };
		int[] array3 = new int[] { 1, 2, 3, 4, 5 };
		int j1 = 1, j2 = 2, j3 = 3;
		int[] array4 = { j1, j2, j3 };

		for (int i = 0; i < array1.length; i++) {
			array1[i] = (i + 1) * 100;
		}

		System.out.println("array1: " + array1);
		for (int i = 0; i < array1.length; i++) {
			System.out.println(array1[i]);
		}

		System.out.println("array1은 " + Arrays.toString(array1));
		System.out.println("array4는 " + Arrays.toString(array4));

	}
}
