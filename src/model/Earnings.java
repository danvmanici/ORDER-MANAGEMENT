package model;

public class Earnings {
	/**
	 * Clasa Earnigs cu getters si setters.
	 */
	int id;
	String productName;
	float price;
	
	public Earnings(int id, String productName, float price) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
	}
	
	public Earnings(String productName, float price) {
		super();
		this.productName = productName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
