package exam02;

public class PhoneBookEx {
	public static void main(String[] args) {
		PhoneBook pb1 = new PhoneBook(); // 객체변수, instance
		pb1.name = "홍길동";
		pb1.phoneNumber = "01011111111";
		pb1.location = "서울";
		
		PhoneBook pb2 = new PhoneBook();
		pb2.name = "정금담";
		pb2.phoneNumber = "01022223333";
		pb2.location = "대구";
		
		PhoneBook pb3 = new PhoneBook();
		pb3.name = "하기훈";
		pb3.phoneNumber = "01044445555";
		pb3.location = "경주";
		
		pb1.display();
		pb2.display();
		pb3.display();
	}
}
