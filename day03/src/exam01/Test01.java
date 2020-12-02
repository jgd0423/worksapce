package exam01;

public class Test01 {
	public static void main(String[] args) {
		int nowYear = 2020;
		int bornYear = 1989;
		int age;
		String name = "정금담";
		String msg = "";
		
		age = nowYear - bornYear + 1;
		
		msg += "당신의 이름은 " + name + "입니다.\n";
		msg += name + "님의 나이는 " + age + "세입니다.";
		System.out.println(msg);
	}
}
