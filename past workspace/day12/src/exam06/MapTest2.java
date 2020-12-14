package exam06;

import java.util.HashMap;

public class MapTest2 {
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("홍길동", 19);
		map.put("이성순", 21);
		map.put("장천용", 27);
		map.put("김석원", 22);
		map.put("이석재", 17);
		System.out.println(map);
		map.put("이석재", 50);
		System.out.println(map);
		map.remove("이석재");
		System.out.println(map);
		
		map.put("이석재", 19);
		for (String name : map.keySet()) {
			System.out.println(name + " / " + map.get(name));
		}
	}
}
