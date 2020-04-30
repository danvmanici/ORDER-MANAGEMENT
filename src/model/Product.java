package model;


public class Product {
	
	/**
	 * Clasa product cu getters si setters.
	 */
	private int id;
	private String name;
	private int quantity;
	private float price; 
	
	public Product(int id, String name,int quantity, float price){
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Product(String name, int quantity, float price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}