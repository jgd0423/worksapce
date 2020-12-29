package chapter10;

import java.util.Optional;

public class Main {
	
	public static String getText() {
		return "AAA";
	}
	
	public static void main(String[] args) {
		String text = getText();
		Optional<String> maybeText = Optional.ofNullable(text);
		int length;
		if (maybeText.isPresent()) {
			length = maybeText
		}
	}

}
