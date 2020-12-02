package exam01;

public class Pracc {
	public static void main(String[] args) {
		int age = 21;
		String job = "student";
		
		if (age > 19) {
			if (!job.equals("student")) {
				System.out.println("출입이 가능합니다.");
			} else {
				System.out.println("학생은 출입이 불가능합니다.");
			}
		} else {
			System.out.println("20세 미만은 출입이 불가능합니다.");
		}
	}
}
