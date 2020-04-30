package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Customer;
import model.Order;
import model.Product;

public class GeneratorPdf {
/**
 * Genereaza pdf pentru clienti.
 * @param clients
 * @param number
 */
	public static void generateClientReport(ArrayList<Customer> clients, int number) {
		String fileName = "clients_report_" + number + ".pdf";
		Document document = new Document();
		try {
			try {
				PdfWriter.getInstance(document, new FileOutputStream(fileName));
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			} catch (DocumentException e1) {

				e1.printStackTrace();
			}
			document.open();
			PdfPTable clientsTable = new PdfPTable(3);
			for (Customer c : clients) {
				addRows(clientsTable, c.getId() + "_" + c.getName() + "_" + c.getLocation());
			}
			document.add(clientsTable);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
/**
 * Genereaza pdf pentru products.
 * @param products
 * @param number
 */
	public static void generateProductReport(ArrayList<Product> products, int number) {
		String fileName = "products_report_" + number + ".pdf";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			PdfPTable productsTable = new PdfPTable(4);
			for (Product p : products) {
				addRows(productsTable, p.getId() + "_" + p.getName() + "_" + p.getQuantity() + "_" + p.getPrice());
			}
			document.add(productsTable);
			document.close();
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
/**
 * Genereaza pdf pentru orders.
 * @param orders
 * @param number
 */
	public static void generateOrderReport(ArrayList<Order> orders, int number) {
		String fileName = "orders_report_" + number + ".pdf";
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			PdfPTable ordersTable = new PdfPTable(4);
			for (Order o : orders) {
				addRows(ordersTable,
						o.getId() + "_" + o.getCustomerName() + "_" + o.getProductName() + "_" + o.getQuantity());
			}
			document.add(ordersTable);
			document.close();
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
/**
 * Adauga rranduri in tabel.
 * @param table
 * @param content
 */
	private static void addRows(PdfPTable table, String content) {
		String display[] = content.split("_");
		for (String thisField : display) {
			table.addCell(thisField);
		}
	}
	/**
	 * Genereaza eroare pentru atunci cand produsul 
	 * nu este pe stoc.
	 * @param product
	 */
	 public static void generateStockError(String product) {
	        String fileName = product + "_stock_err.pdf";
	        Document document = new Document();
	        try {
	            PdfWriter.getInstance(document, new FileOutputStream(fileName));
	            document.open();
	            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
	            Chunk chunk = new Chunk("The product " + product + " is out of stock.", font);
	            document.add(chunk);
	            document.close();
	        } catch (DocumentException | FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
/**
 * Creaza Bill 
 * @param order
 * @param cost costul comenzii
 */
	public static void createBill(Order order, float cost) {
		String fileName = order.getCustomerName()+"_"+order.getProductName() + "_bill.pdf";
		Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph chunk = new Paragraph("Client name: " + order.getCustomerName()+"\nProduct name: " + order.getProductName() + "\n", font);
            Chunk chunk2 = new Chunk("\nThe cost of this order is " + cost, font);
            document.add(chunk);
            document.add(chunk2);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
	}

}
