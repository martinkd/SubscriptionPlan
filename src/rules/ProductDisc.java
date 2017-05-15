package rules;

import java.util.List;

import products.Bundle;
import products.Product;

public abstract class ProductDisc {
	
	protected Bundle bundle;
	protected List<RangeBasedDiscount> discounts;
	
	public ProductDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		this.bundle = bundle;
		this.discounts = discounts;
	}
	
	public void discount() {

		for (Product product : bundle.getProducts()) {
			double discAmount = discAmount(product);
			product.setPrice(product.getPrice() - discAmount);
			bundle.setDiscAmount(bundle.getDiscAmount() + discAmount);
			
			System.out.printf("%s %s discount - %.2f%n", product.getName(), discounts.get(0).getType(), discAmount);
		}
		System.out.println("-------------");

	}
	
	protected abstract double discAmount(Product p);
	
}
