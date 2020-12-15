package exam06;

import java.util.HashMap;

public class MapTest {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("영국", "런던");
		map.put("미국", "워싱턴");
		map.put("호주", "캔버라");
		System.out.println(map);
		map.put("영국", "서울");
		System.out.println(map);
		
		System.out.println("---------------------");
		
		System.out.println(map.get("영국"));
		System.out.println(map.get("미국"));
		
		map.remove("영국");
		System.out.println(map);
		System.out.println(map.size());
		
		for (String s : map.keySet()) {
			System.out.println(s + " / " + map.get(s));
		}
	}
}
