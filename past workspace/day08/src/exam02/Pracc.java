package exam02;

import java.util.ArrayList;

class ClassTest {
	String name;
	String phone;
	String addr;
	static ArrayList<ClassTest> pList = new ArrayList<ClassTest>();

	void display() {
		System.out.println(name + "님의 전화번호는 " + phone + "이며, 주소는 " + addr + "입니다.");
	}
	
	static void put(ClassTest... c) {
		for (int i = 0; i < c.length; i++) {
			pList.add(c[i]);
		}
	}
	
}

public class Pracc {
	public static void main(String[] args) {
		ArrayList<ClassTest> personality = new ArrayList<ClassTest>();

		ClassTest c1 = new ClassTest();
		c1.name = "홍길동";
		c1.phone = "01011112222";
		c1.addr = "경기";

		ClassTest c2 = new ClassTest();
		c2.name = "이성순";
		c2.phone = "01033334444";
		c2.addr = "대구";

		ClassTest c3 = new ClassTest();
		c3.name = "장천용";
		c3.phone = "01055556666";
		c3.addr = "서울";

		personality.add(c1);
		personality.add(c2);
		personality.add(c3);
		
		ClassTest.put(c1, c2, c3);
		System.out.println(ClassTest.pList);

		for (int i = 0; i < personality.size(); i++) {
			personality.get(i).display();
		}
	}
}
