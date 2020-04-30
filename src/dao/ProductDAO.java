package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customer;
import model.Product;

public class ProductDAO {

	protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO products(name,quantity,price) VALUES (?,?,?)";
	private final static String deleteStatementString="DELETE  FROM products WHERE name = ?";
	private final static String findStatementString = "SELECT * FROM products where name = ?";
	private final static String updateStatementString= "UPDATE products SET quantity = ? "+ "WHERE id = ?";
	private final static String selectStatementString="Select * FROM products";
	/**
	 * Functia sterge un produs dupa nume.
	 * @param product
	 */
	 public static void deleteByName(Product product){
		 
	        Connection dbConnection = ConnectionFactory.getConnection();
	        PreparedStatement deleteStatement = null;

	        try{
	        	deleteStatement = dbConnection.prepareStatement(deleteStatementString);
	        	deleteStatement.setString(1, product.getName());
	            deleteStatement.executeUpdate();

	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionFactory.close(deleteStatement);
	            ConnectionFactory.close(dbConnection);
	        }
	    }
	 
/**
 * Functia cauta un produs dupa nume si il returneaza daca 
 * il gaseste.
 * @param product
 * @return
 */
	public static Product findByName(Product product) {
		

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		int id=0;
		String name;
		int quantity;
		float price;
		Product p1 = new Product(0,null,0,0);
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, product.getName());
			rs = findStatement.executeQuery();
			if(rs.next()) {
				
			id = rs.getInt("id");
			name=rs.getString("name");
			quantity = rs.getInt("quantity");
			price = rs.getFloat("price");
			p1=new Product(id,name,quantity,price);

			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return p1;
	}
	/**
	 * Functia insereaza un produs in tabelul products.
	 * Aduna cantitatile pentru produsele cu acelasi nume.
	 * @param product
	 */
	public static void insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		
		if(findByName(product).getId()==0)
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.setString(1, product.getName());
			insertStatement.setInt(2, product.getQuantity());
			insertStatement.setFloat(3, product.getPrice());
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		else {
			try {
				updateStatement = dbConnection.prepareStatement(updateStatementString);
				updateStatement.setInt(1, (findByName(product).getQuantity()+product.getQuantity()));
				updateStatement.setInt(2, findByName(product).getId());
				updateStatement.executeUpdate();

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
			} finally {
				ConnectionFactory.close(updateStatement);
				ConnectionFactory.close(dbConnection);
			}
			
		}
		}
	/**
	 * Returneaza o lista cu toate produsele.
	 * @return
	 */
	public static ArrayList<Product> select(){
		 
		ArrayList<Product> lista = new ArrayList<Product>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        Product p=null;
        try{
        	selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs=selectStatement.executeQuery();
            
            while(rs.next()) {
            	int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                p=new Product(id,name,quantity,price);
                lista.add(p);
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

