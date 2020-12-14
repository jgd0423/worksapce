package exam01;

import java.util.Scanner;

public class Test03 {
	public static void main(String[] args) {
		//입력 : 이름, 월급, 보너스(%)
		//출력 : 이름, 월급, 보너스(%), 총 연봉
		
		Scanner scanner = new Scanner(System.in);
		
		final int MONTHES = 12;
		String name;
		int salary;
		int bonusPercentage;
		int salariesOfYear;
		int wholeBonuses;
		int annualSalary;
		String message = "";
		
		System.out.print("이름: ");
		name = scanner.next();
		
		System.out.print("월급: ");
		salary = scanner.nextInt();
		
		System.out.print("보너스: ");
		bonusPercentage = scanner.nextInt();
		
		salariesOfYear = salary * MONTHES;
		wholeBonuses = salary * bonusPercentage / 100;
		annualSalary = salariesOfYear + wholeBonuses;
		
		message += "이름\t월급\t보너스\t연봉\n";
		message += name + "\t" + salary + "\t" + wholeBonuses + "\t" + annualSalary;
		System.out.println(message);
	}
}
