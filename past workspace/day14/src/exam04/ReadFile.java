package exam04;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	// Field
	String path;
	ArrayList<String[]> studentInfos; 
	
	// Constructor
	public ReadFile(String path) {
		this.path = path.replace("\\", "\\\\");
		this.studentInfos = getStudentInfos();
//		this.path = path;
//		System.out.println(this.path);
	}
	
	// Method
	public ArrayList<String[]> getStudentInfos() {
		ArrayList<String[]> studentInfos = new ArrayList<String[]>();
		
		System.out.println(path);
		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] studentInfo = {};
				studentInfo = data.split(",");
				studentInfos.add(studentInfo);
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		
		return studentInfos;
	}
}