package chapter10;

import java.util.Optional;

public class Main {
	
	public static String getText() {
		return "AAA";
	}
	
	public static void main(String[] args) {
		int length = Optional.ofNullable(getText()).map(String::length).orElse(0);
		
		
		System.out.println(length);
	}

}
