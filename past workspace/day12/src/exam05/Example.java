package exam05;

import java.util.ArrayList;
import java.util.Scanner;

public class Example {
	static ArrayList<BookSale> books = new ArrayList<BookSale>();
	static Scanner sc = new Scanner(System.in);
	
	public static void inputBook() {
		while (true) {
			System.out.print("책이름: ");
			
			String bookName = sc.next();
			if (bookName.toLowerCase().equals("q")) {
				break;
			}
			
			System.out.print("저자: ");
			String author = sc.next();
			
			System.out.print("출판사: ");
			String maker = sc.next();
			
			System.out.print("출판연도: ");
			int year = sc.nextInt();
			
			System.out.print("가격: ");
			int price = sc.nextInt();
			
			System.out.print("수량: ");
			int amount = sc.nextInt();
			
			System.out.print("랭크: ");
			int rank = sc.nextInt();
			
			BookSale b1 = new BookSale(bookName, author, maker, year, price, amount, rank);
			books.add(b1);			
		}
	}
	
	public static void modifyBook() {
		System.out.print("수정할 책의 번호 입력: ");
		int modifyNum = sc.nextInt() - 1;
		BookSale selectedBook = books.get(modifyNum);
		
		System.out.print("책이름: ");
		selectedBook.setBookName(sc.next());
		
		System.out.print("저자: ");
		selectedBook.setAuthor(sc.next());
		
		System.out.print("출판사: ");
		selectedBook.setMaker(sc.next());
		
		System.out.print("출판연도: ");
		selectedBook.setYear(sc.nextInt());
		
		System.out.print("가격: ");
		selectedBook.setPrice(sc.nextInt());
		
		System.out.print("수량: ");
		selectedBook.setAmount(sc.nextInt());
		
		System.out.print("랭크: ");
		selectedBook.setRank(sc.nextInt());
	}
	
	public static void deleteBook() {
		System.out.print("지울 책의 번호 입력: ");
		int deleteNum = sc.nextInt() - 1;
		books.remove(deleteNum);
	}
	
	public static void main(String[] args) {
		while (true) {
			String menuMessage = "--------------------------------\n" +
		                         "0:종료, 1:목록, 2:등록, 3:수정, 4:삭제\n" +
		                         "--------------------------------";
			System.out.println(menuMessage);
			System.out.print("선택: ");
			String menuSelect = sc.next();
			
			if (menuSelect.equals("1")) {
				System.out.println("번호\t책이름\t저자\t출판사\t출판연도\t가격\t판매수량\t랭크\t총판매액");
				for (int i = 0; i < books.size(); i++) {
					System.out.print((i + 1) + "\t");
					books.get(i).display();
				}
			} else if (menuSelect.equals("2")) {
				inputBook();		
			} else if (menuSelect.equals("3")) {
				modifyBook();
			} else if (menuSelect.equals("4")) {
				deleteBook();
			}
			else if (menuSelect.equals("0")) {
				System.out.println("-- 프로그램 종료 --");
				break;
			}
		}
	}
}
