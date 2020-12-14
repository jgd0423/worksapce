package exam09;

import java.util.ArrayList;
import java.util.HashMap;

public class Example1 {
	public static void main(String[] args) {
		ArrayList<HashMap<String, String>> members = new ArrayList<>();
		
		HashMap<String, String> member = new HashMap<>();
		member.put("id", "hong");
		member.put("password", "1234");
		member.put("phone", "010-1111-1111");
		member.put("name", "홍길동");
		members.add(member);
		
		member = new HashMap<>();
		member.put("id", "jung");
		member.put("password", "23432");
		member.put("phone", "010-2323-3243");
		member.put("name", "이경태");
		members.add(member);
		
		System.out.println(members);
	}
}
