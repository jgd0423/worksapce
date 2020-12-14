package exam01;

import java.util.Scanner;

public class ReceiptUsingWhile {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String message = "";
		int totalPrice = 0;
		int totalPoint = 0;
		
		while (true) {
			System.out.print("상품명을 입력하세요: ");
			String name = sc.next();
			int point = 0;
			if (name.toLowerCase().equals("q")) {
				break;
			}
			
			System.out.print("상품 수량을 입력하세요: ");
			int quantity = sc.nextInt();
			
			System.out.print("상품 가격을 입력하세요: ");
			int price = sc.nextInt();
			
			int priceOfQuantity = quantity * price;
			if (priceOfQuantity > 5000) {
				point = priceOfQuantity * 10 / 100;
			}
			
			totalPrice += priceOfQuantity;
			totalPoint += point;
			
			message += name + "\t.....\t" + quantity + "   ..\t" + 
			           priceOfQuantity + "원 " + "(" +  price + "원) ... " + point + "P\n";
		}
		message += "----------------------------------\n";
		message += "합계\t.....\t" + totalPrice + "원 ... " + totalPoint + "P";
		System.out.println(message);
		System.out.println("-- 프로그램 종료 --");
	}
}
