package rules;

import java.util.List;

import products.Bundle;
import products.Product;

public class ProductPercDisc extends ProductDisc{

	public ProductPercDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		super(bundle, discounts);
	}

	@Override
	protected double discAmount(Product p) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (p.getPrice() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount() * p.getPrice() / 100.0;
			}
		}
		return NO_DISCOUNT;
	}

}
