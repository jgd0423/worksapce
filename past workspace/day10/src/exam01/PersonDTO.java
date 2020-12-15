package exam01;
// DTO, VO, Bean

// DAO - 데이타베이스 연결과 관련있음

public class PersonDTO {

	// Field
	// 이름, 나이, 키, 몸무게
	String name;
	int age;
	double height;
	double weight;

	// Constructor
	public PersonDTO(String name, int age, double height, double weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public PersonDTO() {}
	// Method

}
