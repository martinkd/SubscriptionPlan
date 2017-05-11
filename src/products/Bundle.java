package products;

import java.util.ArrayList;
import java.util.List;

public class Bundle extends Product {

	private List<Product> products;

	public Bundle() {
		products = new ArrayList<Product>();
	}

	public void add(Product product) {
		products.add(product);
		price += product.getPrice();
		
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public int getSize() {
		return products.size();
	}

}
