package bll;



import java.util.ArrayList;

import dao.CustomerDAO;
import model.Customer;


public class CustomerBLL {

	/**
	 * Clasa care contine logica operatiilor pentru customer.
	 */
	public CustomerBLL() {
		
	}

	public static void deleteCustomer(Customer customer) {
		CustomerDAO.deleteByName(customer);
		
	}

	public static void insertCustomer(Customer customer) {
		
		 CustomerDAO.insert(customer);
	}
	
	public static ArrayList<Customer> select(){
		
		return CustomerDAO.select();
		
		
	}
}
