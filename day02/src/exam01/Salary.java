package exam01;

public class Salary {
	public static void main(String[] args) {
		String name = "김철수";
		final int MONTHES = 12;
		int salary = 1000000;
		int bonusPercentage = 5;
		int salariesOfYear;
		int wholeBonuses;
		int totalSalaries;
		String message = "";
		
		salariesOfYear = salary * MONTHES;
		wholeBonuses = salary * bonusPercentage;
		totalSalaries = salariesOfYear + wholeBonuses;
		
		message += "---------------------------------------------------\n";
		message += "이름\t";
		message += "월급\t\t";
		message += "보너스\t\t";
		message += "총연봉액\n";
		message += "---------------------------------------------------\n";
		message += name + "\t";
		message += salariesOfYear + "\t";
		message += wholeBonuses + "\t\t";
		message += totalSalaries + "\n";
		message += "---------------------------------------------------";
		
		System.out.println(message);
	}
}
