package exam05;

import java.util.ArrayList;

public class BookSale extends Book {
	// Field
	private int amount; // 판매수
	private int rank; // 순위
	private long money; // 판매금액 (단가 * 수량, 10만원 초과 시 10% 할인)
	ArrayList<BookSale> books = new ArrayList<BookSale>();

	// Constructor
	public BookSale() {
	}

	public BookSale(String bookName, String author, String maker, int year, int price, int amount, int rank) {
		super(bookName, author, maker, year, price);
		this.amount = amount;
		this.rank = rank;
		this.money = calcMoney();
	}

	// Method
	public long calcMoney() {
		long money = (long) getPrice() * amount;
		if (money > 100000) {
			money = (long) (money * 0.9);
		}

		return money;
	}

	public void display() {
		System.out.println(getBookName() + "\t" + getAuthor() + "\t" + getMaker() + "\t" + getYear() + "\t" + getPrice()
				+ "\t" + amount + "\t" + rank + "\t" + money);
	}

	// Getter, Setter
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

}
