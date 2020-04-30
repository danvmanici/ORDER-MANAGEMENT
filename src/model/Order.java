package model;


public class Order{

/**
 * Clasa Orders cu getters si setters.
 */
	private int id;
    private String customerName;
    private String productName;
	private int quantity;
	 
	

	public Order(int id, String customerName, String productName, int  quantity) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.productName = productName;
		this. quantity =  quantity;
	}

	public Order(String customerName, String productName, int  quantity) {
		super();
		this.customerName = customerName;
		this.productName = productName;
		this.quantity =  quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return  quantity;
	}

	public void setQuantity(int quantity) {
		this. quantity = quantity;
	}


}