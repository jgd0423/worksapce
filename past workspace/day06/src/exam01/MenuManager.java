package exam01;

import java.util.Scanner;

public class MenuManager {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String menuMessage = "";
		menuMessage += "-----------------------\n";
		menuMessage += "0:종료, 1:목록, 2:등록\n";
		menuMessage += "-----------------------\n";
		menuMessage += "선택하세요 : ";
		
		while (true) {
			System.out.print(menuMessage);
			String choiceMenu = sc.next();
			if (choiceMenu.equals("1")) {
				System.out.println("-- 목록 --");
			} else if (choiceMenu.equals("2")) {
				System.out.println("-- 등록 --");
			} else if (choiceMenu.equals("0")) {
				break;
			}
			System.out.println();
		}
		System.out.println("-- 프로그램 종료 --");
	}
}

// 프로그램 종료를 루프 안에 넣는 것과 루프 밖에 두는 것 중에서 뭐가 더 좋을까?
// 프로그램 종료를 루프 안에 넣으면 while의 조건을 (true)로 설정할 수 있다.
// 가독성 측면에서 while (!choiceMenu.equals("0"))보다는 while (true)가 좋을듯?
// 프로그램의 목적을 파악하기에는 어떤 것이 좋은가? (단순한 가독성보다는)
// 함수명에서 어떤 프로그램인지 충분히 인지 가능하다면 (true)로 적는게 낫다.