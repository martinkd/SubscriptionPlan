package rules;

import products.Product;

public class TotalSumDiscount extends RangeBasedDiscount {

	public TotalSumDiscount(double low, double high, double discount) {
		super(low, high, discount);
	}

	@Override
	public void discount(Product p) {
		p.setPrice(p.getPrice() - getDiscount());
	}

}
