package exam09Exception;

import java.util.*;

// 예외: 일반예외, 실행예외

public class Test01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		try {
			int[] numbers = { 1, 2, 3, 4, 5 };
			System.out.println(numbers[9]);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("예외 발생");
		} finally {
			System.out.println("무조건 실행된다");
		}
	}
}
