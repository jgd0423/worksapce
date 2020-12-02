package exam01;

public class Test01 {
	// 실행과 관련있음
	public static void main(String[] args) {
		/**
		 * 기본자료형(primitive data type) : 8개
		 	* 정수형 : byte(1byte), short(2byte), int(4byte), long(8byte)
		 	* 실수형 : float(4byte), double(8byte)
		 	* 문자형 : char
		 	* 논리형 : boolean
		 * 참조자료형(reference data type) : 만들어서 사용
		 * */
		
		byte b = 1;
		System.out.println("a의 값은 " + b + "입니다.");
		System.out.println(Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
		
		short s = 1;
		System.out.println("s의 값은 " + s + "입니다.");
		System.out.println(Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
		
		int i = 1;
		System.out.println("i의 값은 " + i + "입니다.");
		System.out.println(Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
		
		long l = 1L;
		System.out.println("l의 값은 " + l + "입니다.");
		System.out.println(Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
		
		float f = 3.14F;
		System.out.println("f의 값은 " + f + "입니다.");
		
		double d = 3.14;
		System.out.println("d의 값은 " + d + "입니다.");
		
		// 연산을 할 때는 데이타 타입을 맞추어야 한다.
		System.out.println(f - d);
		
		char c = 'a';
		System.out.println(c);
		c = 65;
		System.out.println(c);
		

		
	}
}
