package exam07;

class Tmp {
	// Field
	String name;
	int money;
	static double iyul = 0.09;
	double total;

	// Constructor
	public Tmp(String name, int money) {
		this.name = name;
		this.money = money;
		total = money + (money * iyul);
		
		System.out.println(name + "|" + total);
	}
	
	// Method
}

public class Test000 {
	public static void main(String[] args) {
		Tmp.iyul = 0.01;
		Tmp t1 = new Tmp("홍길동", 10000);
		System.out.println(t1.iyul);
		Tmp t2 = new Tmp("이성순", 30000);
		Tmp t3 = new Tmp("장천용", 50000);
		
	}
}
