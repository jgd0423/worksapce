package exam08File;

import java.io.FileWriter;

public class WriteToFile {
	public static void main(String[] args) {
		try {
			FileWriter myWriter = new FileWriter("D:\\jgd\\attach\\test.txt");
			myWriter.write("안녕하세요!!");
			myWriter.write("안녕하세요!!");
			myWriter.write("안녕하세요!!");
			myWriter.write("안녕하세요!!");
			myWriter.write("안녕하세요!!");
			myWriter.close();
			System.out.println("성공");
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
	}
}
