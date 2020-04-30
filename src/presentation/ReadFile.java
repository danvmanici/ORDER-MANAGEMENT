package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import bll.CustomerBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Customer;
import model.Order;
import model.Product;

public class ReadFile {

	private String fisier;
	private static ArrayList<String> lista = new ArrayList<String>();
	int reportClient = 0;
	int reportProduct = 0;
	int reportOrder = 0;

	public ReadFile(String fisier) {
		super();
		this.fisier = fisier;
		readFile();
	}

	public ReadFile() {

	}

	public String getFisier() {
		return fisier;
	}
/**
 * Citeste datele din fisier si le adauga
 * intr-o lista.
 */
	public static void readFile() {

		try {
			File myObj = new File("fisier");
			Scanner myScanner = new Scanner(myObj);
			while (myScanner.hasNextLine()) {
				String data = myScanner.nextLine();
				lista.add(data);
			}
			myScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error!\n");
		}
	}

	public ArrayList<String> getLista() {
		return lista;
	}
/**
 * Imparte cuvintele din comenzile din lista
 * primita ca parametru, pentru a le putea executa.
 * @param lista
 */
	public void parseCommand(ArrayList<String> lista) {
		for (String l : lista) {
			if (l.equalsIgnoreCase("Report client"))
				GeneratorPdf.generateClientReport(CustomerBLL.select(), ++reportClient);
			else if (l.equalsIgnoreCase("Report order"))
				GeneratorPdf.generateOrderReport(OrderBLL.select(), ++reportProduct);
			else if (l.equalsIgnoreCase("Report product"))
				GeneratorPdf.generateProductReport(ProductBLL.select(), ++reportOrder);
			else {
				String[] parsedInfo = l.split(": ");
				String[] parsedData = parsedInfo[1].split(", ");
				if (parsedInfo[0].equalsIgnoreCase("Insert client")) {
					Customer c = new Customer(parsedData[0], parsedData[1]);
					CustomerBLL.insertCustomer(c);
				} else if (parsedInfo[0].equalsIgnoreCase("Delete client")) {
					Customer c = new Customer(parsedData[0], parsedData[1]);
					CustomerBLL.deleteCustomer(c);
				} else if (parsedInfo[0].equalsIgnoreCase("Insert product")) {
					Product p = new Product(parsedData[0], Integer.parseInt(parsedData[1]),
							Float.parseFloat(parsedData[2]));
					ProductBLL.insert(p);
				} else if (parsedInfo[0].equalsIgnoreCase("Delete product")) {
					Product p = new Product(parsedData[0], 0, 0);
					ProductBLL.deleteByName(p);
				} else if (parsedInfo[0].equalsIgnoreCase("Order")) {
					Order o = new Order(parsedData[0], parsedData[1], Integer.parseInt(parsedData[2]));
					OrderBLL.insert(o);
				}
			}
		}
	}
}