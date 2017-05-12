package rules;

import java.util.List;

import products.Bundle;
import products.Product;

public class ProductFixDisc extends ProductDisc{

	public ProductFixDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		super(bundle, discounts);
	}

	@Override
	protected double discAmount(Product p) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (p.getPrice() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount();
			}
		}
		return 0;
	}

}
