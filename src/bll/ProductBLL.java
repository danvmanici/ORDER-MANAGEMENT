package bll;


import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;


public class ProductBLL {

	/**
	 * Clasa care contine logica operatiilor pentru produs.
	 */
	public ProductBLL() {
		
	}


	public static void insert(Product product) {
		
		ProductDAO.insert(product);
	}

	 public static void deleteByName(Product product){
		 
		 ProductDAO.deleteByName(product);
		 
	 }
	 
	 public void findByName(Product product) {
		 
		 ProductDAO.findByName(product);
	 }
	 
	 public static ArrayList<Product> select(){
		 
		return	ProductDAO.select();
			
		}
}