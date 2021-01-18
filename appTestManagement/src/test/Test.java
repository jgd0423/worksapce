package test;

public class Test {
	public static void main(String[] args) {
		int a[] = new int[100];
		int sw = 1;
		if (sw == 1) {
			for (int i = 0; i < a.length; i++) {
				a[i] = 100;
			}
		} else if (sw == 2) {
			for (int i = 0; i < a.length; i++) {
				a[i] = 200;
			}
		} else {
			for (int i = 0; i < a.length; i++) {
				a[i] = 300;
			}
		}
	}
}
