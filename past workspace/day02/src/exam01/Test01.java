package exam01;

public class Test01 {
	// ����� ��������
	public static void main(String[] args) {
		/**
		 * �⺻�ڷ���(primitive data type) : 8��
		 	* ������ : byte(1byte), short(2byte), int(4byte), long(8byte)
		 	* �Ǽ��� : float(4byte), double(8byte)
		 	* ������ : char
		 	* ���� : boolean
		 * �����ڷ���(reference data type) : ���� ���
		 * */
		
		byte b = 1;
		System.out.println("a�� ���� " + b + "�Դϴ�.");
		System.out.println(Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
		
		short s = 1;
		System.out.println("s�� ���� " + s + "�Դϴ�.");
		System.out.println(Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
		
		int i = 1;
		System.out.println("i�� ���� " + i + "�Դϴ�.");
		System.out.println(Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
		
		long l = 1L;
		System.out.println("l�� ���� " + l + "�Դϴ�.");
		System.out.println(Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
		
		float f = 3.14F;
		System.out.println("f�� ���� " + f + "�Դϴ�.");
		
		double d = 3.14;
		System.out.println("d�� ���� " + d + "�Դϴ�.");
		
		// ������ �� ���� ����Ÿ Ÿ���� ���߾�� �Ѵ�.
		System.out.println(f - d);
		
		char c = 'a';
		System.out.println(c);
		c = 65;
		System.out.println(c);
		

		
	}
}
