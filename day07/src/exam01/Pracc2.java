package exam01;

import java.util.ArrayList;
import java.util.Scanner;

public class Pracc2 {
	public static void main(String[] args) {
		           //제너릭
		ArrayList<ArrayList> scoreInfos = new ArrayList<ArrayList>();
		ArrayList<String> scoreInfo = new ArrayList<String>();
		String[] ments = {"이름", "국어", "영어", "수학"};
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			for (int i = 0; i < ments.length; i++) {
				System.out.print(ments[i] + ": ");
				String temp = sc.next();
				scoreInfo.add(temp);
			}
			scoreInfos.add(scoreInfo);
			scoreInfo = new ArrayList<String>();
			
			if (scoreInfos.size() == 3) {
				break;
			}
		}
		System.out.println(scoreInfos);
	
	}
}
