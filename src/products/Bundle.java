package products;

import java.util.ArrayList;
import java.util.List;

public class Bundle {
	
	private double discAmount;
	private double price;
	private String name;
	private List<Product> products;
	
	public Bundle() {
		products = new ArrayList<Product>();
	}
	
	public void add(Product product) {
		products.add(product);
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public int getSize() {
		return products.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		double sum = 0;
		for (Product product : products) {
			sum += product.getPrice();
		}
		price = sum;
		return price;
	}

	public double getDiscAmount() {
		return discAmount;
	}

	public void setDiscAmount(double discAmount) {
		this.discAmount = discAmount;
	}

}
