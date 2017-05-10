package rules;

import products.Product;

public class NumberItemsDiscount extends RangeBasedDiscount{

	public NumberItemsDiscount(double low, double high, double discount) {
		super(low, high, discount);
	}

	@Override
	public void discount(Product p) {
		p.setPrice(p.getPrice() - getDiscount());
	}
	
}
