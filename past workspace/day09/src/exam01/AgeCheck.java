package exam01;

import java.util.Scanner;

public class AgeCheck {
	// Field


	// Constructor
	public AgeCheck() {

	}

	// Method
	public void canDrink(int age) {
		String msg = "술을 마실 수 없습니다.";
		if (age >= 20) {
			msg = "술을 마실 수 있습니다.";
		}
		
		System.out.println(msg);
	}

	public static void main(String[] args) {
		// 나이를 받아서 결과 출력
		Scanner sc = new Scanner(System.in);
		System.out.print("나이를 입력하세요: ");
		int age = sc.nextInt();
		AgeCheck a1 = new AgeCheck();
		a1.canDrink(age);
	}
}
