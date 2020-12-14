package ex01;

import java.util.Scanner;

public class Ex01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String dbId = "hong";
		String dbPwd = "h1234";
		
		System.out.print("아이디: ");
		String inputId = sc.next();
		
		System.out.print("비밀번호: ");
		String inputPwd = sc.next();
		
		if (dbId.equals(inputId) && dbPwd.equals(inputPwd)) {
			System.out.println("일치");
		} else {
			System.out.println("불일치");
		}
	}
}
