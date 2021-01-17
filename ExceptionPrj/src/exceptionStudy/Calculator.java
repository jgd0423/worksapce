package exceptionStudy;

public class Calculator {
	
	// Constructor
	public Calculator() {}
	
	// Method
	public static int add(int x, int y) {
		int result = x + y;
		
		if (result > 1000) {
			throw new exceptionsOverThousand();
		}
		
		if (result < 0) {
			throw new exceptionsUnderZero();
		}
		
		return result;
	}
	
	public static int sub(int x, int y) {
		int result = x + y;
		
		if (result < 0) {
			throw new exceptionsUnderZero();
		}
		
		return result;
	}
	
	public static int multi(int x, int y) {
		return x * y;
	}
	
	public static int div(int x, int y) {
		return x / y;
	}
}
