package exam05;

class Dog {
	// Field
	String dogName;
	int dogAge;
	
	// Constructor
	public Dog(String dogName, int dogAge) {
		this.dogName = dogName;
		this.dogAge = dogAge;
	}
	
	// Method
}

class Person {
	// Field
	String personName;
	int personAge;
	Dog dog; // 참조자료형, 클래스 타입의 자료형

	// Constructor
	public Person(String personName, int personAge, Dog dog) {
		this.personName = personName;
		this.personAge = personAge;
		this.dog = dog;
	}

	// Method
	public void display() {
		System.out.println("사람 이름: " + personName);
		System.out.println("사람 나이: " + personAge);
		System.out.println("개의 이름: " + dog.dogName);
		System.out.println("개의 나이: " + dog.dogAge);
	}

	@Override
	public String toString() {
		return "Person [personName=" + personName + ", personAge=" + personAge + ", dog=" + dog + "]";
	}
	
	
}

public class ReferenceType {
	public static void main(String[] args) {
		Dog dog1 = new Dog("땡칠이", 3);
		Person person1 = new Person("정금담", 32, dog1);
		person1.display();
		
		System.out.println(person1.toString());
		
		
	}
}
