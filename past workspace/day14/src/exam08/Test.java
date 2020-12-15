package exam08;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<>();
		System.out.println(list);
		list.add("하나");
		System.out.println(list);
		list.add("5");
		System.out.println(list);
		list.add(5);
		System.out.println(list);
		list.add(5.5);
		System.out.println(list);
		list.add(false);
		System.out.println(list);

		System.out.println(list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
