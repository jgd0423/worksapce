package exam01;

import java.util.Scanner;

public class ReceiptUsingFor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String message = "";
		int totalPrice = 0;
		
		for (int i = 0; i < 5; i++) {
			System.out.print("상품명을 입력하세요: ");
			String name = sc.next();
			
			System.out.print("상품 수량을 입력하세요: ");
			int quantity = sc.nextInt();
			
			System.out.print("상품 가격을 입력하세요: ");
			int price = sc.nextInt();
			
			int priceOfQuantity = quantity * price;
			
			totalPrice += priceOfQuantity;
			
			message += name + "\t.....\t" + quantity + "   ..\t" + 
			           priceOfQuantity + "원 " + "(" +  price + "원)\n";
		}
		message += "----------------------------------\n";
		message += "합계\t.....\t" + totalPrice + "원";
		System.out.println(message);
		System.out.println("-- 프로그램 종료 --");
	}
}
