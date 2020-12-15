package test3;

public class ProductDTO {
	// Field
	private String name;
	private int price;
	private double saledPrice;
	
	// Constructor
	public ProductDTO() {}
	
	
	// Method
	
	// Getters and Setters
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

	public double getSaledPrice() {
		return saledPrice;
	}

	public void setSaledPrice(double saledPrice) {
		this.saledPrice = saledPrice;
	}
}
