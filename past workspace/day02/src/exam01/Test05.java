package exam01;

public class Test05 {
	public static void main(String[] args) {
		int kor = 83;
		int eng = 93;
		int mat = 81;
		int total;
		double avg;
		String message = "";
		
		total = kor + eng + mat;
		avg = (double) total / 3;
		
		message = message + "±¹¾î\t";
		message = message + "¿µ¾î\t";
		message = message + "¼öÇĞ\t";
		message = message + "ÃÑÁ¡\t";
		message = message + "Æò±Õ\n";
		message = message + kor + "\t";
		message = message + eng + "\t";
		message = message + mat + "\t";
		message = message + total + "\t";
		message = message + avg;
		
		System.out.println(message);
		
	}
}
