package shop.model.dto;

import java.sql.Timestamp;

public class ProductDTO {
	// Field
	private int no;
	private String name;
	private int price;
	private String description;
	private String product_img;
	private int amount;
	private Timestamp regi_date;
	
	// Constructor
	public ProductDTO() {}
	
	// Getters and Setters
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public Timestamp getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Timestamp regi_date) {
		this.regi_date = regi_date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
