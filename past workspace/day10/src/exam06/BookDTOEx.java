package exam06;

import java.util.Scanner;

public class BookDTOEx {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("책이름: ");
		String bookName = sc.nextLine();
		
		System.out.print("저자: ");
		String author = sc.next();
		
		System.out.print("출판사: ");
		String maker = sc.next();
		
		System.out.print("출판연도: ");
		String year = sc.next();
		
		System.out.print("단가: ");
		int price = sc.nextInt();
		
		System.out.print("판매수량: ");
		int number = sc.nextInt();
		
		BookDTO b1 = new BookDTO(bookName, author, maker, year, price, number);
		b1.display();
		
	}
}
