package exam03;

import java.io.File;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) {
		
		try {
			File myObj = new File("D:\\jgd\\attach\\test.txt");
			Scanner myReader = new Scanner(myObj);
			System.out.println("이름,국어,영어,수학,과학,역사,총점,평균");
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] studentInfo = data.split(",");
				int tot = Integer.parseInt(studentInfo[1]) + 
						  Integer.parseInt(studentInfo[2]) + 
						  Integer.parseInt(studentInfo[3]) + 
						  Integer.parseInt(studentInfo[4]) + 
						  Integer.parseInt(studentInfo[5]);
				double avg = (double) tot / 5;
				System.out.println(data + "," + tot + "," + avg);
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
}