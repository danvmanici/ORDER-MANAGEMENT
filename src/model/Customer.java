package model;



public class Customer {

	/**
	 * Clasa Customer cu getters si setters.
	 */
	private int id=0;
	private String name;
	private String location;

	public Customer(int id, String name, String location) {
		super();
		this.id=id;
		this.name = name;
		this.location=location;
	}

	public Customer(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public Customer() {
		
	}

	public int getId() {
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
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}