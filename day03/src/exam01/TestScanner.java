package exam01;

import java.util.Scanner;

public class TestScanner {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
//		System.out.print("숫자를 입력하세요: ");
//		int num1 = scanner.nextInt(); // 숫자를 입력받는다.
//		System.out.println(num1);
		
//		System.out.print("주소를 입력하세요: ");
//		String address = scanner.next(); // 공백 앞까지만 인식, 띄어쓰기 전까지만 입력받는다.
//		scanner.nextLine();
//		
//		System.out.print("이름을 입력하세요: ");
//		scanner.nextLine();
//		
//		String name = scanner.nextLine(); // 전체 라인 인식, 키보드 버퍼가 비어있으면 입력을 요구, 비어있지 않으면 버퍼의 전체 내용을 읽어옴.
//		System.out.println("주소: " + address);
//		System.out.println("이름: " + name);
		
		System.out.print("숫자를 입력하세요: ");
		int num1 = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("주소를 입력하세요: ");
		String addr = scanner.nextLine();
		
		System.out.println("num1의 값은" + num1 + "이고, addr의 값은 " + addr + "입니다.");
	}
}
