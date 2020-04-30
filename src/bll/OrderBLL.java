package bll;



import java.util.ArrayList;

import dao.OrderDAO;
import model.Earnings;
import model.Order;


	public class OrderBLL {

		/**
		 * Clasa care contine logica operatiilor pentru order.
		 */
		public OrderBLL() {
			
		}


		public static void insert(Order order) {
			
			OrderDAO.insert(order);
		}
	
		public static ArrayList<Order> select(){
			
			return OrderDAO.select();
			
		}
		
		public static void insertInEarnings(Earnings earnings) {
			OrderDAO.insertInEarnings(earnings);
		}
}
