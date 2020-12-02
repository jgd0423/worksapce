package exam01;

import java.util.Arrays;
import java.util.Scanner;

public class ReceiptObject {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] receipts = new String[5];
		int totalPrice = 0;
		String message = "";
		
		for (int i = 0; i < receipts.length; i++) {
			System.out.print("상품 이름: ");
			String name = sc.next();
			
			System.out.print("상품 가격: ");
			int price = sc.nextInt();
			totalPrice += price;
			
			String receipt = "";
			receipt += name + "|";
			receipt += price;
			
			receipts[i] = receipt;			
		}
		
		for (int i = 0; i < receipts.length; i++) {
			String[] receipt = receipts[i].split("\\|");
			String name = receipt[0];
			String price = receipt[1];
			
			message += name + "\t.....\t" + price + "\n";
		}
		 
		message += "==========================\n";
		message += "합계\t.....\t" + totalPrice;
		
		System.out.println(message);
	}
}
