package exam01;

public class Test04 {
	public static void main(String[] args) {
		// 형변환: 자동형변환, 묵시적형변환(Promotion), 강제형변환, 명시적형변환(Casting)
		
		
		int iValue1 = 2147483647;
		float fValue1 = 3.0F;
		double dValue1 = 3.0;
		
		fValue1 = iValue1;
		dValue1 = iValue1;
		
		System.out.println(fValue1);
		System.out.println(dValue1);
	}
}
