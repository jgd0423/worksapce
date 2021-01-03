package product;

import java.util.Comparator;

public class Exam {
	public Runnable r = () -> {
		System.out.println(this);
		System.out.println(toString());
	};
	
	public String toString() {
		return "Hello's custom toString()";
	}
	
	
	public static void main(String[] args) {
		Exam h = new Exam();
		h.r.run();
		
		Comparator<String> c = new Comparator<String>() {

			@Override
			public int compare(String lhs, String rhs) {
				return lhs.compareTo(rhs);
			}
		};
		int result = c.compare("Hello", "World");
		

		Comparator<String> c2 = (lhs, rhs) -> lhs.compareTo(rhs);
		int result2 = c2.compare("Hello", "World");
		
		
		Comparator<String> c3 = (lhs, rhs) -> {
			System.out.println("I am comparing " + lhs + " to " + rhs);
			return lhs.compareTo(rhs);
		};
		int result3 = c3.compare("Hello", "World");
		
		
		
		
	}
}
