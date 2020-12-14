package exam01;

import java.util.Arrays;

public class Test01 {
	public static void main(String[] args) {
		int[] imsi1 = new int[3];
		int[] imsi2 = imsi1;
		
		imsi1[0] = 10;
		imsi1[1] = 20;
		imsi1[2] = 30;
		
		imsi2[0] = 80;
		
		System.out.println(imsi1[0]);
		System.out.println(Arrays.toString(imsi1));
		System.out.println(Arrays.toString(imsi2));
		
		String a = "hong";
		String b = a;
		
		a += "a";
		
		
		System.out.println(b);
		System.out.println(imsi1 == imsi2);
		System.out.println(imsi1.equals(imsi2));
		System.out.println(a == b);
	}
}
