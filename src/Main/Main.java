package Main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import bll.CustomerBLL;
import model.Customer;
import model.Order;
import model.Product;
import presentation.ReadFile;
import dao.ConnectionFactory;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;

@SuppressWarnings("unused")
public class Main {
	
	protected static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
	
	public static void main(String[] args)  throws SQLException{
	
		ReadFile f=new ReadFile("fisier");
		f.parseCommand(f.getLista());
	}
	
	

}
