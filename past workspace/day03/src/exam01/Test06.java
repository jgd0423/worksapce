package exam01;

import java.util.Scanner;

public class Test06 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int firstNumber;
		int secondNumber;
		String operationSymbol;
		int integerResult;
		double realNumberResult;
		
		System.out.print("첫 번째 정수를 입력하세요: ");
		firstNumber = scanner.nextInt();
		
		System.out.print("두 번째 정수를 입력하세요: ");
		secondNumber = scanner.nextInt();
		
		System.out.print("연산자를 입력하세요(+, -, *, /): ");
		operationSymbol = scanner.next();
		
		switch (operationSymbol) {
		case "+":
			integerResult = firstNumber + secondNumber;
			System.out.println(integerResult);
			break;
		case "-":
			integerResult = firstNumber - secondNumber;
			System.out.println(integerResult);
			break;
		case "*":
			integerResult = firstNumber * secondNumber;
			System.out.println(integerResult);
			break;
		case "/":
			realNumberResult = (double) firstNumber / secondNumber;
			System.out.println(realNumberResult);
			break;
		default:
			System.out.println("실행없음");
			break;
		}
	}
}
