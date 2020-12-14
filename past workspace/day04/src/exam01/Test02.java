package exam01;

import java.util.Scanner;

public class Test02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		int dan;		
//		System.out.print("몇 단? : ");
//		dan = sc.nextInt();
//		for (int i=1; i<10; i++) {
//		System.out.println(dan + " * " + i + " = " + (dan * i));
//		}
		
//		for (int k=0; k<10; k++) {
//			if (k == 5) {
//				//break; //현재 반복문을 빠져나감
//				continue;
//			}
//			System.out.println("현재 k의 값은 " + k + "입니다.");
//		}
//		System.out.println("-- 프로그램 종료 --");
		
		for (int i=0; i<5; i++) {
			if (i == 2) {
				continue;
			}
			for (int j=0; j<=3; j++) {
				System.out.println(i + " / " + j);
			}
			System.out.println("-------------------------------------------");
		}
	}
}
