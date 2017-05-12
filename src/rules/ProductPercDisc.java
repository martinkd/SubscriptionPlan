package rules;

import java.util.List;

import products.Bundle;
import products.Product;

public class ProductPercDisc {

	private Bundle bundle;
	private List<RangeBasedDiscount> discounts;

	public ProductPercDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		this.bundle = bundle;
		this.discounts = discounts;
	}
	
	public void discount() {
		
		for (Product product : bundle.getProducts()) {
			double discAmount = discAmount(product);
			product.setPrice(product.getPrice() - discAmount);
			System.out.println(product.getName() + " percentage discount - " + discAmount);
		}

	}

	private double discAmount(Product p) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (p.getPrice() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount()*p.getPrice()/100.0;
			}
		}
		return 0;
	}


}
