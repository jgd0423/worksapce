package exam01;

import java.util.Scanner;

public class StarTriangle2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int height;

		System.out.print("높이를 입력하세요 : ");
		height = sc.nextInt();
		
		long beforeTime1 = System.currentTimeMillis();
		for (int i = height; i >= 1; i--) {
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		long afterTime1 = System.currentTimeMillis(); 
		long secDiffTime1 = (afterTime1 - beforeTime1)/1000;
	
	//------------------------------------------------------------------------------
		
		long beforeTime2 = System.currentTimeMillis();
		String temp = "";
		for (int i = 1; i <= height; i++) {
			temp += "*";
		}
		
		for (int j = height; j >= 1; j--) {
			System.out.println(temp.substring(0, j));
		}
		
		long afterTime2 = System.currentTimeMillis(); 
		long secDiffTime2 = (afterTime2 - beforeTime2)/1000;
		
		System.out.println(secDiffTime1);
		System.out.println(secDiffTime2);
	}
}
