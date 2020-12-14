package exam08File;

import java.io.File;
import java.io.IOException;

// 예외(Exception)처리
// 예외: 프로그램 실행 중에 발생하는 문제 <- 개발자 해결o
// 에러: 하드웨어, OS(운영체제) <- 개발자 해결x
// try { 실행문 실행문 실행문 } catch(Exception e) { e.printStackTrace(); } finally { 정상적이든 예외가 발생했든 무조건 실행하는 부분 }

public class CreateFile {
	public static void main(String[] args) {
		File myObj = new File("D:\\jgd\\attach\\test.txt");
		
		try {
			if (myObj.createNewFile()) {
				System.out.println("File created : " + myObj.getName());
			} else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
