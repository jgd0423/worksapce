package exam09;

import java.util.ArrayList;

public class Example {
	public static void main(String[] args) {
		ArrayList<Member> members = new ArrayList<>();
		
		Member m1 = new Member();
		m1.setId("hong");
		m1.setPassword("1234");
		m1.setName("홍길동");
		m1.setPhone("010-1111-1111");
		members.add(m1);
		
		Member m2 = new Member();
		m2.setId("jung");
		m2.setPassword("0987");
		m2.setName("정원겸");
		m2.setPhone("010-2222-2222");
		members.add(m2);
		
		Member m3 = new Member();
		m3.setId("kim");
		m3.setPassword("34645");
		m3.setName("김진태");
		m3.setPhone("010-3333-3333");
		members.add(m3);

		
		for (Member member : members) {
			System.out.println(member.getId());
			System.out.println(member.getPassword());
			System.out.println(member.getName());
			System.out.println(member.getPhone());
		}
	}
}
