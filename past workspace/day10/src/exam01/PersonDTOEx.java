package exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersonDTOEx {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("이름: ");
		String name = br.readLine();
		
		System.out.print("나이: ");
		String age_ = br.readLine();
		int age = Integer.parseInt(age_);
		
		System.out.print("키: ");
		String height_ = br.readLine();
		double height = Double.parseDouble(height_);
		
		System.out.print("몸무게: ");
		String weight_ = br.readLine();
		double weight = Double.parseDouble(weight_);
		
		PersonDTO p1 = new PersonDTO(name, age, height, weight);
		
		System.out.println("name: " + p1.name);
		System.out.println("age: " + p1.age);
		System.out.println("height: " + p1.height);
		System.out.println("weight: " + p1.weight);
		
	}
}
