package exam08File;

import java.io.File;

public class GetFileInfo {
	public static void main(String[] args) {
		File myObj = new File("D:\\jgd\\attach\\test.txt");
		if(myObj.exists()) {
			System.out.println("파일이름: " + myObj.getName());
			System.out.println("절대경로: " + myObj.getAbsolutePath());
			System.out.println("사이즈: " + myObj.length());
		} else {
			System.out.println("파일없음");
		}
	}
}
