package products;

import java.util.ArrayList;
import java.util.List;

public class Bundle extends Product {

	private List<Product> bundle;

	public Bundle() {
		bundle = new ArrayList<Product>();
	}

	public void add(Product product) {
		bundle.add(product);
		
	}

	public List<Product> getBundle() {
		return bundle;
	}
	
	@Override
	public double getPrice() {
		price = 0;
		for (Product product : bundle) {
			price += product.getPrice();
		}
		return price;
	}
	
	public int getSize() {
		return bundle.size();
	}

}
