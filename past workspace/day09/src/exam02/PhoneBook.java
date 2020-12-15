package exam02;

public class PhoneBook {
	// Field
	String name;
	String phoneNumber;
	String location;
	
	// Constructor
	public PhoneBook() {

	}
	
	// Method
	public void display() {
		System.out.println(name + "\t" + phoneNumber + "\t" + location);
	}
}
