package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import model.Customer;
import model.Earnings;
import model.Order;
import model.Product;
import presentation.GeneratorPdf;

public class OrderDAO {

	protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO orders(customerName,productName,quantity) VALUES (?,?,?)";
	private final static String updateStatementString = "UPDATE products SET quantity = ? " + "WHERE id = ?";
	private final static String selectStatementString = "Select * FROM orders";
	private static final String insertStatementString2 = "INSERT INTO earnings(productName,price) VALUES (?,?)";
	/**
	 * Creaza orders daca produsul este disponibil pe stoc si
	 * updateaza stocul produsului. Insereaza in earnings produsul 
	 * si cat s-a castigat din vanzarea acestuia.
	 * @param order
	 */
	public static void insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();
		Product p = new Product(order.getProductName(), order.getQuantity(), 0);
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.setString(1, order.getCustomerName());
			insertStatement.setString(2, order.getProductName());
			insertStatement.setInt(3, order.getQuantity());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		}
		if (ProductDAO.findByName(p).getId() != 0) {
			try {
				if (ProductDAO.findByName(p).getQuantity() - order.getQuantity() >= 0) {
					updateStatement = dbConnection.prepareStatement(updateStatementString);
					updateStatement.setInt(1, (ProductDAO.findByName(p).getQuantity() - order.getQuantity()));
					updateStatement.setInt(2, ProductDAO.findByName(p).getId());
					updateStatement.executeUpdate();
					float cost = order.getQuantity() * ProductDAO.findByName(p).getPrice();
					Earnings e=new Earnings(ProductDAO.findByName(p).getName(),cost);
					insertInEarnings(e);
					GeneratorPdf.createBill(order, cost);
				} else {
					GeneratorPdf.generateStockError(p.getName());
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
			} finally {
				ConnectionFactory.close(insertStatement);
				ConnectionFactory.close(updateStatement);
				ConnectionFactory.close(dbConnection);
			}
		}
	}
/**
 * Returneaza o lista cu toate orders.
 * @return
 */
	public static ArrayList<Order> select() {

		ArrayList<Order> lista = new ArrayList<Order>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		Order o = null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String customerName = rs.getString("customerName");
				String productName = rs.getString("productName");
				int quantity = rs.getInt("quantity");
				o = new Order(id, customerName, productName, quantity);
				lista.add(o);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return lista;
	}
	/**
	 * Functia de insert in earnings produsul si cat s-a castigat din vanzarea acestuia.
	 * @param earnings
	 */
	public static void insertInEarnings(Earnings earnings) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString2);
			insertStatement.setString(1, earnings.getProductName());
			insertStatement.setFloat(2, earnings.getPrice());
			insertStatement.executeUpdate();	
			} catch (SQLException e1) {
				LOGGER.log(Level.WARNING, "OrderDAO|Earnings:insert " + e1.getMessage());
			} finally {
				ConnectionFactory.close(insertStatement);
				ConnectionFactory.close(dbConnection);
			}
		}
	
}
