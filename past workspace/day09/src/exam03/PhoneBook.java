package exam03;

import java.util.Scanner;

public class PhoneBook {
	// Field
	String name;
	String number;
	String location;
	
	// Constructor : Overloading
//	public PhoneBook(String name, String number, String location) {
//		this.name = name;
//		this.number = number;
//		this.location = location;
//	}
	
	public PhoneBook() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름: ");
		name = sc.next();
		System.out.print("번호: ");
		number = sc.next();
		System.out.print("지역: ");
		location = sc.next();
		
		display();
	}
	
	// method
	public void display() {
		System.out.println(name + "\t" + number + "\t" + location);
	}
}
