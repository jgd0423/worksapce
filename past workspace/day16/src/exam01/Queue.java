package exam01;

import java.util.Scanner;

public class Queue {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int waitNumber = 1;

		while (true) {
			System.out.print("선택하세요(0:종료, 1:다음번호출력): ");
			String selectMenu = sc.next();
			if (selectMenu.equals("0")) {
				break;
			} else if (selectMenu.equals("1")) {
				if (waitNumber == 101) {
					waitNumber = 1;
				}
				System.out.println("대기번호: " + waitNumber);
				waitNumber += 1;
			}
		}

		System.out.println("-- 프로그램 종료 --");
	}
}
