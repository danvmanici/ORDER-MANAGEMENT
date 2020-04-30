package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customer;

public class CustomerDAO {

	protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO customers(name,location) VALUES (?,?)";
	private final static String deleteStatementString="DELETE  FROM customers WHERE name = ?";
	private final static String selectStatementString="Select * FROM customers";
	
	/**
	 * Sterge un client dupa nume.
	 * @param customer
	 */
	 public static void deleteByName(Customer customer){
		 
	        Connection dbConnection = ConnectionFactory.getConnection();
	        PreparedStatement deleteStatement = null;

	        try{
	        	deleteStatement = dbConnection.prepareStatement(deleteStatementString);
	        	deleteStatement.setString(1, customer.getName());
	            deleteStatement.executeUpdate();

	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionFactory.close(deleteStatement);
	            ConnectionFactory.close(dbConnection);
	        }
	    }
	 /**
	  * Insereaza un client.
	  * @param customer
	  */
	public static void insert(Customer customer) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.setString(1, customer.getName());
			insertStatement.setString(2, customer.getLocation());
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "CustomerDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	/**
	 * Returneaza o lista cu totii clientii din baza de date.
	 * @return
	 */
	public static ArrayList<Customer> select(){
		
		ArrayList<Customer> lista = new ArrayList<Customer>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        Customer c=null;
        ResultSet rs = null;
        try{
        	selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs=selectStatement.executeQuery();
            
            while(rs.next()) {
            	int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                c=new Customer(id,name,location);
                lista.add(c);
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
        return lista;
    }
}