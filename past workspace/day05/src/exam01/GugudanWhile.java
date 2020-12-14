package exam01;

import java.util.Scanner;

public class GugudanWhile {
	public static void main(String[] args) {
		
		// y를 누르면 계속, 아니면 탈출
		Scanner sc = new Scanner(System.in);
		String exit = "y";
		
		
		while (exit.equals("y")) {
			int danNumber;
			String msg = "";
			System.out.print("단을 입력하세요 : ");
			danNumber = sc.nextInt();
			
			for (int i = 1; i <= 9; i++) {
				msg += danNumber + " * " + i + " = " + danNumber * i + "\n";
			}
			msg += "==============\n";
			System.out.println(msg);
			System.out.print("계속하려면 y를 입력하세요 : ");
			exit = sc.next();
		} 
	}
}
