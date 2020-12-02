package exam01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Receipt {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<HashMap<String, String>> products = new ArrayList<>();
		ArrayList<HashMap<String, String>> buyingList = new ArrayList<>();
		ArrayList<String> barcodes = new ArrayList<>();
		barcodes.add("code001");
		barcodes.add("code002");
		barcodes.add("code003");

		HashMap<String, String> product1 = new HashMap<>();
		product1.put("이름", "맥주");
		product1.put("가격", "1500");
		product1.put("할인", "3");

		HashMap<String, String> product2 = new HashMap<>();
		product2.put("이름", "소주");
		product2.put("가격", "2000");
		product2.put("할인", "7");

		HashMap<String, String> product3 = new HashMap<>();
		product3.put("이름", "막걸리");
		product3.put("가격", "1000");
		product3.put("할인", "20");

		products.add(product1);
		products.add(product2);
		products.add(product3);

		while (true) {
			System.out.print("선택하세요(0:종료, 1:결제, 2:구매): ");
			String selectMenu = sc.next();

			if (selectMenu.equals("2")) {
				while (true) {
					System.out.print("구매할 품목을 선택하세요 (code001, code002, code003 중 선택, 종료:q): ");
					String productCode = sc.next();
					if (barcodes.contains(productCode)) {
						int idx = barcodes.indexOf(productCode);
						buyingList.add(products.get(idx));
					} else if (productCode.equals("q")) {
						break;
					} else {
						System.out.println("다시 입력하세요");
					}
				}
			} else if (selectMenu.equals("1")) {
				int totalPrice = 0;
				
				for (HashMap<String, String> product : buyingList) {
					System.out.println(product);
					int price = Integer.parseInt(product.get("가격"));
					int discountRate = Integer.parseInt(product.get("할인"));
					int discountPrice = price - (price * discountRate / 100);
					totalPrice += discountPrice;
				}

				System.out.println("총 가격: " + totalPrice);
			} else if (selectMenu.equals("0")) {
				break;
			}
		}

		System.out.println("-- 프로그램 종료 -- ");
	}
}
