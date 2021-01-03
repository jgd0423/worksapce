package product;

import java.util.Comparator;

public class Person {
	// Field
	public String firstName;
	public String lastName;
	public int age;
	
	// Constructor
	public Person(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	// Method
	public final static Comparator<Person> compareFirstName = 
			(lhs, rhs) -> lhs.firstName.compareTo(rhs.firstName);

	public final static Comparator<Person> compareLastName = 
			(lhs, rhs) -> lhs.lastName.compareTo(rhs.lastName);
	
	public static int compareFirstNames(Person lhs, Person rhs) {
		return lhs.firstName.compareTo(rhs.firstName);
	}
			
	public String toString() {
		return "[Person: firstName:" + firstName + " " + 
				"lastName:" + lastName + " " + "age:" + age + "]";
	}
}
