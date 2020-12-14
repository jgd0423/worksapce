package exam01;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ListTest01 {
	public static void main(String[] args) {
		ArrayList cars = new ArrayList();
		cars.add("아반떼");
		System.out.println("값 추가: " + cars);

		cars.add("그랜저");
		System.out.println("값 추가: " + cars);

		cars.add("제네시스");
		System.out.println("값 추가: " + cars);

		cars.add(1, "소나타");
		System.out.println("값 추가(인덱스 이용): " + cars);

		cars.add("프라이드");
		System.out.println("값 추가: " + cars);
		
		cars.add(1);
		System.out.println("값 추가(int): " + cars);
		
		ArrayList k = new ArrayList(Arrays.asList(1, 2, 3));
		cars.add(k);
		System.out.println("값 추가(list): " + cars);
		
		cars.set(1, "울티마");
		System.out.println("값 대체: " + cars);
		
		cars.remove(0);
		System.out.println("0번째 인덱스 값 삭제: " + cars);
		
		boolean a = cars.remove("제네시스");
		System.out.println("리스트에 '제네시스' 삭제: " + a);
		
		cars.add("그랜저");
		cars.add("그랜저");
		System.out.println("'그랜저' 두 번 추가: " + cars);

		System.out.println("리스트 길이: " + cars.size());

		cars.remove("그랜저");
		System.out.println("'그랜저' remove에 넣으면 첫번째값 삭제: " + cars);
		
		boolean r = cars.contains("그랜저");
		System.out.println("'그랜저' 리스트에 있는지 확인: " +  r);

		System.out.println(cars.indexOf("프라이드"));
	}
}
