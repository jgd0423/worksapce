package com.test.spring01.test;

public class Test07DTO {
	private String section;
	private String name;
	private int price;
	private int discount;
	private int discountedPrice;
	private String maker;
	
	public void calcDiscountedPrice() {
		this.discountedPrice = this.price - (this.price * this.discount / 100);
		
	}
	
	// Getters and Setters
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
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
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
}
