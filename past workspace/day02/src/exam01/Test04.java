package exam01;

public class Test04 {
	public static void main(String[] args) {
		// ����ȯ: �ڵ�����ȯ, ����������ȯ(Promotion), ��������ȯ, ���������ȯ(Casting)
		
		
		int iValue1 = 2147483647;
		float fValue1 = 3.0F;
		double dValue1 = 3.0;
		
		fValue1 = iValue1;
		dValue1 = iValue1;
		
		System.out.println(fValue1);
		System.out.println(dValue1);
	}
}
