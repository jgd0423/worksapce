package exam01;

import java.util.Scanner;

public class MethodPracc {

	static void receipt(String name, String price) {
		System.out.println("입력한 상품은 " + name + "이며, 가격은 " + price + "원입니다.");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		while (true) {
			System.out.print("상품 이름: ");
			String name = sc.next();
			
			if (name.toLowerCase().equals("q")) {
				break;
			}
			
			System.out.print("상품 가격: ");
			String price = sc.next();
			
			receipt(name, price);
		}
		System.out.println("-- 프로그램 종료 --");
	}
}
