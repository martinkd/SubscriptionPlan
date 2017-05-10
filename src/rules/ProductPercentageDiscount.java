package rules;

import products.Product;

public class ProductPercentageDiscount extends RangeBasedDiscount {

	public ProductPercentageDiscount(double low, double high, double discount) {
		super(low, high, discount);
	}

	@Override
	public void discount(Product p) {
		double calcDiscount = p.getPrice() - (p.getPrice() * getDiscount() / 100.0);
		p.setPrice(calcDiscount);
	}

}
