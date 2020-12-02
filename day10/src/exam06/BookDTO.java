package exam06;

public class BookDTO {
	// Field
	private String bookName;
	private String author;
	private String maker;
	private String year;
	private int price;
	private int number;
	private int money;

	// Constructor
	public BookDTO(String bookName, String author, String maker, String year, int price, int number) {
		this.bookName = bookName;
		this.author = author;
		this.maker = maker;
		this.year = year;
		this.price = price;
		this.number = number;
		money = calcMoney();
	}

	// Method
	public int calcMoney() {
		int money = price * number;
		return money;
	}

	public void display() {
		String msg = "";
		msg += "책이름\t\t";
		msg += "저자\t";
		msg += "출판사\t\t";
		msg += "출판연도\t";
		msg += "단가\t";
		msg += "판매수량\t";
		msg += "총판매액\n";

		msg += bookName + "\t";
		msg += author + "\t";
		msg += maker + "\t";
		msg += year + "\t\t";
		msg += price + "\t";
		msg += number + "\t\t";
		msg += money;

		System.out.println(msg);
	}
}
