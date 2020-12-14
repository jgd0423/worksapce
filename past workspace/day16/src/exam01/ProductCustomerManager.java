package exam01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProductCustomerManager {
	static ArrayList<HashMap<String, String>> products = new ArrayList<>();

	public static void productManager() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("선택(1:상품목록, 2:상품등록, 기타:종료): ");
			String selectMenu = sc.next();
			if (selectMenu.equals("1")) {
				if (products.size() == 0) {
					System.out.println("상품목록이 비어있습니다.");
				} else {
					int totalPrice = 0;
					for (HashMap<String, String> product : products) {
						String productName = product.get("productName");
						String productPrice_ = product.get("productPrice");
						int productPrice = Integer.parseInt(productPrice_);
						totalPrice += productPrice;
						System.out.println(productName + "\t" + productPrice_);
					}
					System.out.println("----------------");
					System.out.println("총가격\t" + totalPrice);
				}
			} else if (selectMenu.equals("2")) {
				HashMap<String, String> product = new HashMap<>();
				System.out.println("상품등록");

				System.out.print("상품명: ");
				String productName = sc.next();
				System.out.print("상품가격: ");
				String productPrice = sc.next();
				product.put("productName", productName);
				product.put("productPrice", productPrice);
				products.add(product);
			} else {
				System.out.println("-- 상품관리 종료 --");
				break;
			}
		}
	}

	public static void customerManager() {
		System.out.println("-- 상품관리 --");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("선택(1:상품관리, 2:회원관리, 기타:종료): ");
			String selectMenu = sc.next();
			if (selectMenu.equals("1")) {
				productManager();
			} else if (selectMenu.equals("2")) {
				customerManager();
			} else {
				System.out.println("-- 프로그램 종료 --");
				break;
			}
		}
	}
}
