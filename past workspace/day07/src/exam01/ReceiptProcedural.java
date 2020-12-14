package exam01;

import java.util.Scanner;

public class ReceiptProcedural {
	public static void main(String[] args) {
		//합계: 상품이름, 상품가격 5개
		Scanner sc = new Scanner(System.in);
		String[] products = new String[5];
		int[] prices = new int[5];
		int totalPrice = 0;
		String message = "";
		
		for (int i = 0; i < products.length; i++) {
			System.out.print("상품 이름: ");
			String product = sc.next();
			
			System.out.print("상품 가격: ");
			int price = sc.nextInt();
			
			totalPrice += price;
			products[i] = product;
			prices[i] = price;
		}
		
		for (int i = 0; i < products.length; i++) {
			message += products[i] + "\t.....\t" + prices[i] + "\n"; 
		}
		
		message += "==========================\n";
		message += "합계\t.....\t" + totalPrice;
		
		System.out.println(message);
	}
}
