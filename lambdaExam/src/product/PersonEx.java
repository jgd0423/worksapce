package product;

import java.util.Arrays;

public class PersonEx {
	public static void main(String[] args) {
		Person[] people = new Person[] {
			new Person("Ted", "Neward", 41),
			new Person("Charlotte", "Neward", 41),
			new Person("Michael", "Neward", 19),
			new Person("Matthew", "Neward", 13)
		};
		
		// Sort by first name
		Arrays.sort(people, Person.compareFirstName);
		for (Person p : people) {
			System.out.println(p);
		}
		
		// Person 객체 생성 안했는데 comapreFirstNames 사용할 수 있음 :: 써서. 
		Arrays.sort(people, Person::compareFirstNames);
		for (Person p : people) {
			System.out.println(p);
		}

	}
}
