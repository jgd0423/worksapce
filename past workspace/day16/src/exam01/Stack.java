package exam01;

import java.util.ArrayList;
import java.util.Scanner;

public class Stack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> stackList = new ArrayList<>();
		
		while (true) {
			System.out.print("선택하세요(0:종료, 1:배송, 2:등록): ");
			String selectMenu = sc.next();
			
			if (selectMenu.equals("2")) {
				while (true) {
					System.out.print("등록할 물건: ");
					String stuff = sc.next();
					if (stuff.toLowerCase().equals("q")) {
						break;
					}
					
					stackList.add(stuff);
				}
			} else if (selectMenu.equals("1")) {
				if (stackList.size() == 0) {
					System.out.println("리스트가 비어있습니다.");
				} else {
					System.out.println(stackList.get(stackList.size() - 1));
					stackList.remove(stackList.size() - 1);
				}
			} else if (selectMenu.equals("0")) {
				break;
			}
		}
		
		System.out.println("-- 프로그램 종료 --");
	}
}
